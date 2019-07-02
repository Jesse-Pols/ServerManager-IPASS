package de.jonashackt.springbootvuejs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.jonashackt.springbootvuejs.domain.Dienst;
import de.jonashackt.springbootvuejs.repository.DienstRepository;

@RestController()
@RequestMapping("/api")
public class MainController extends BackendController {
	
    @Autowired
    private DienstRepository dienstRepository;
	
	@RequestMapping(path = "/secured", method = RequestMethod.GET)
	public @ResponseBody String getSecured() {
        return "Succesfully called getSecured()!";
	}

	@RequestMapping(path = "/user", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody int addNewDinest (@RequestParam String name, @RequestParam String key, @RequestParam String relevance) {
		Dienst dienst = new Dienst(name, key, relevance);
		dienstRepository.save(dienst);

		System.out.println(dienst.toString() + " Succesfully saved into DB");
		return dienst.getId();
	}

}
