package de.jonashackt.springbootvuejs.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class BackendController {
	
	public static final String SECURED_TEXT = "Secuur!";
	public static final String BASE_URL = "de.jonashackt.springbootvuejs.controller.BackendController.java";
	
    protected boolean checkRequest(String query) throws IOException {
    	try {
    		URL url = new URL(query);
    		HttpURLConnection con = (HttpURLConnection) url.openConnection();
    		con.setRequestMethod("GET");
    		int status = con.getResponseCode();
            if (status > 299)
    		    return false;
            return true;
    	} catch (Exception ex)
    	{ System.out.println(BASE_URL + ".checkRequest() Failed: " + ex); return false; }
    }

}
