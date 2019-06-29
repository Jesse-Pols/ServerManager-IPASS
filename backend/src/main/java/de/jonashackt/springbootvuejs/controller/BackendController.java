package de.jonashackt.springbootvuejs.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.jonashackt.springbootvuejs.dao.DienstOracleDaoImpl;
import de.jonashackt.springbootvuejs.domain.Dienst;

@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);
    public static final String SECURED_TEXT = "Hello from the secured resource!";    
    public static final String BASE_URL = "de.jonashackt.springbootvuejs.controller.BackendController.java";

    // Get all Diensten and check each one's status
    @RequestMapping (path = "/dienst/get")
    public @ResponseBody JSONObject getDiensten() {
        LOG.info("GET called on /dienst/get. \nGetting all diensten...\nChecking each Dienst's status...");

        DienstOracleDaoImpl dodi = new DienstOracleDaoImpl();
        List<Dienst> diensten = dodi.findAll(); 
        JSONObject jsonObject = new JSONObject();

        try {
            for (Dienst dienst : diensten) {
                // boolean content = this.checkRequest(dienst.getKey());
                boolean content = true;
                if (content) dienst.setStatus("Beschikbaar");
                else dienst.setStatus("Niet Beschikbaar");
            }
            jsonObject.put("Diensten", diensten);
        } catch (Exception ex) {
            System.out.println(BASE_URL + ".checkStatus() Failed: " + ex);
            jsonObject.put("Error", "Couldn't check the status");
        }
        return jsonObject;
    }

    // Get dienst key by id
    @RequestMapping (path = "/dienst/get/{id}", method = RequestMethod.GET)
    public @ResponseBody String getKeyById(@PathVariable("id") int id) {
        DienstOracleDaoImpl dodi = new DienstOracleDaoImpl();
        return dodi.findKeyById(id);
    }
    
    @RequestMapping(path = "/dienst/create/{name}/{key}", method = RequestMethod.POST)
    public @ResponseBody boolean createDienstNoRelevance(@PathVariable("name") String name, @PathVariable("key") String key) {
    	return createDienst(name, key, null);
    }
    
    @RequestMapping(path = "/dienst/create/{name}/{key}/{relevance}", method = RequestMethod.POST)
    public @ResponseBody boolean createDienstNoRelevance(@PathVariable("name") String name, @PathVariable("key") String key, @PathVariable("relevance") String relevance) {
    	return createDienst(name, key, relevance);
    }
    
    public boolean createDienst(String name, String key, String relevance) {
        LOG.info("GET called on /createDienst resource. \nCreating dienst....");

        DienstOracleDaoImpl dodi = new DienstOracleDaoImpl();
        Dienst dienst = new Dienst(name, key);
        if (relevance != null) {
            dienst.setRelevance(relevance);
        }

        System.out.println(dienst);
        return dodi.save(dienst);
    }

    @RequestMapping(path = "/secured", method = RequestMethod.GET)
    public @ResponseBody String getSecured() {
        LOG.info("GET successfully called on /secured resource");
        return "Succesfully called getSecured()!";
    }
    
    private boolean checkRequest(String query) throws IOException {
    	try {
    		URL url = new URL(query);
    		HttpURLConnection con = (HttpURLConnection) url.openConnection();
    		con.setRequestMethod("GET");
    		int status = con.getResponseCode();
            if (status > 299)
    		    return false;
            return true;
    	} catch (Exception ex)
    	{ return false; }
    }

}
