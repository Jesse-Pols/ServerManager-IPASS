package de.jonashackt.springbootvuejs.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.jonashackt.springbootvuejs.dao.DienstOracleDaoImpl;
import de.jonashackt.springbootvuejs.domain.Dienst;
import de.jonashackt.springbootvuejs.domain.User;
import de.jonashackt.springbootvuejs.exception.UserNotFoundException;
import de.jonashackt.springbootvuejs.repository.UserRepository;

@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";
    public static final String SECURED_TEXT = "Hello from the secured resource!";
    
    public static final String BASE_URL = "de.jonashackt.springbootvuejs.controller.BackendController.java";

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("unchecked")
	@RequestMapping(path = "/status")
    public @ResponseBody JSONObject checkStatus() {
        LOG.info("GET called on /status resource. \nChecking status of server....");
        // Check database for servers
        // Elke dienst checken op status
        // Alle diensten als JSON object terug sturen

        DienstOracleDaoImpl dodi = new DienstOracleDaoImpl();
        List<Dienst> diensten = dodi.findAll();
        JSONObject jsonObject = new JSONObject();
        
        try {
        	
        	for (Dienst dienst : diensten) {
        		// Request doen naar de status
        		boolean content = this.newRequest(dienst.getKey());
        		//boolean content = true;
        		if (content)
        			dienst.setStatus("Beschikbaar");
        		else
        			dienst.setStatus("Niet Beschikbaar");
        	}
        	
        	// Alle diensten worden in jsonObject.diensten opgeslagen en teruggestuurd met de response
        	jsonObject.put("Diensten", diensten);
        	return jsonObject;
        	
        } catch (Exception ex)
        { System.out.println(BASE_URL + ".checkStatus() Failed: " + ex); }
        
        jsonObject.put("Error", "Couldn't check the status");
        return jsonObject;

    }

    @RequestMapping(path = "/hello")
    public @ResponseBody String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }

    @RequestMapping(path = "/user/{lastName}/{firstName}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody long addNewUser (@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) {
        User savedUser = userRepository.save(new User(firstName, lastName));

        LOG.info(savedUser.toString() + " successfully saved into DB");

        return savedUser.getId();
    }

    @GetMapping(path = "/user/{id}")
    public @ResponseBody User getUserById(@PathVariable("id") long id) {

        return userRepository.findById(id).map(user -> {
            LOG.info("Reading user with id " + id + " from database.");
            return user;
        }).orElseThrow(() -> new UserNotFoundException("The user with the id " + id + " couldn't be found in the database."));
    }

    @RequestMapping(path="/secured", method = RequestMethod.GET)
    public @ResponseBody String getSecured() {
        LOG.info("GET successfully called on /secured resource");
        return SECURED_TEXT;
    }
    
    private boolean newRequest(String query) throws IOException {
    	try {
    		URL url = new URL(query);
    		HttpURLConnection con = (HttpURLConnection) url.openConnection();
    		con.setRequestMethod("GET");
    		int status = con.getResponseCode();
    		return true;
    	} catch (Exception ex)
    	{ return false; }
    }

}
