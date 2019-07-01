package de.jonashackt.springbootvuejs.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.jonashackt.springbootvuejs.dao.DienstOracleDaoImpl;
import de.jonashackt.springbootvuejs.domain.Dienst;

@RestController()
@RequestMapping("/api/dienst")
public class DienstController extends BackendController {
	
	public static final String BASE_URL = "de.jonashackt.springbootvuejs.controller.DienstController.java";
	
	/* GET ALL */
	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/get")
	public @ResponseBody JSONObject getDiensten() {
		
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
	
	/* GET KEY BY ID */
    @RequestMapping (path = "/get/{id}", method = RequestMethod.GET)
    public @ResponseBody String getKeyById(@PathVariable("id") int id) {
        DienstOracleDaoImpl dodi = new DienstOracleDaoImpl();
        return dodi.findKeyById(id);
    }
    
    /* CREATE */    
    @RequestMapping(path = "/create/{name}/{key}", method = RequestMethod.POST)
    public @ResponseBody boolean createDienstNoRelevance(@PathVariable("name") String name, @PathVariable("key") String key) {
    	return createDienst(name, key, null);
    }
    
    @RequestMapping(path = "/create/{name}/{key}/{relevance}", method = RequestMethod.POST)
    public @ResponseBody boolean createDienstNoRelevance(@PathVariable("name") String name, @PathVariable("key") String key, @PathVariable("relevance") String relevance) {
    	return createDienst(name, key, relevance);
    }    
    
    public boolean createDienst(String name, String key, String relevance) {
        DienstOracleDaoImpl dodi = new DienstOracleDaoImpl();
        Dienst dienst = new Dienst(name, key);
        if (relevance != null) {
            dienst.setRelevance(relevance);
        }

        System.out.println(dienst);
        return dodi.save(dienst);
    }
    
    /* DELETE */
    @RequestMapping(path = "/delete/{id}")
    public @ResponseBody boolean deleteDienst(@PathVariable("id") int id) {
        DienstOracleDaoImpl dodi = new DienstOracleDaoImpl();
        Dienst dienst = new Dienst(id);
        dodi.delete(dienst);
        return true;
    }
    
    /* UPDATE */
    @RequestMapping(path = "/update/{id}/{name}/{key}/{relevance}")
    public @ResponseBody boolean updateDienst(@PathVariable("id") int id, @PathVariable("name") String name, @PathVariable("key") String key, @PathVariable("relevance") String relevance) {
        DienstOracleDaoImpl dodi = new DienstOracleDaoImpl();
        Dienst dienst = new Dienst(id, name, key);        
        if (!relevance.contains("None")) dienst.setRelevance(relevance);

        dodi.update(dienst);
        return true;
    }
    
    

}