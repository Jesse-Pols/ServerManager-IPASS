package de.jonashackt.springbootvuejs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
public class LogController extends BackendController {
	
	@RequestMapping(path = "/logs/get")
	public @ResponseBody String getAll() {
		return "asdasdasdasdasdsasssdasdasdasdasdasdasaaaaaaaaaa";
	}

}
