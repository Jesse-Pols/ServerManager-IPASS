package de.jonashackt.springbootvuejs.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.jonashackt.springbootvuejs.domain.Dienst;

@RestController()
@RequestMapping("/api/dienst")
public class DienstController extends BackendController {
	
	public static final String BASE_URL = "de.jonashackt.springbootvuejs.controller.DienstController.java";
	
	/* GET ALL */
	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/get")
	public @ResponseBody JSONObject getDiensten() {
		
        List<Dienst> diensten = dodi.findAll(); 
        JSONObject jsonObject = new JSONObject();

        try {
            for (Dienst dienst : diensten) {
                //boolean content = this.checkRequest(dienst.getKey());
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
	
	/* GET KEY BY ID */
    @RequestMapping (path = "/get/{id}", method = RequestMethod.GET)
    public @ResponseBody String getKeyById(@PathVariable("id") int id) {
        return dodi.findKeyById(id);
    }
    
    /* CREATE */    
    @RequestMapping(path = "/create")
    public @ResponseBody boolean createDienst(@RequestHeader("dienst-name") String name, @RequestHeader("dienst-key") String key, @RequestHeader("dienst-relevance") String relevance) {
        Dienst dienst = new Dienst(name, key);
        if (!relevance.contains("None")) dienst.setRelevance(relevance);
        return dodi.save(dienst);
    }
    
    /* DELETE */
    @RequestMapping(path = "/delete/{id}")
    public @ResponseBody boolean deleteDienst(@PathVariable("id") int id) {
        Dienst dienst = new Dienst(id);
        return dodi.delete(dienst);
    }
    
    /* UPDATE */
    @RequestMapping(path = "/update/{id}")
    public @ResponseBody boolean updateDienst(@PathVariable("id") int id, @RequestHeader("dienst-key") String key, @RequestHeader("dienst-name") String name, @RequestHeader("dienst-relevance") String relevance) {
        Dienst dienst = new Dienst(id, name, key);        
        if (!relevance.contains("None")) dienst.setRelevance(relevance);
        return dodi.update(dienst);
    }
    
    

}
