package de.jonashackt.springbootvuejs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class MainController extends BackendController {
	
	@RequestMapping(path = "/secured", method = RequestMethod.GET)
	public @ResponseBody String getSecured() {
        return "Succesfully called getSecured()!";
	}

}
