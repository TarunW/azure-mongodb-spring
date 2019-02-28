package com.tk.app.tinyurl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/shorturl")
public class TinyUrlController {

	@Autowired
	private TinyUrlService service;

	@RequestMapping(value = "/create/", method = RequestMethod.POST)
	public TinyUrl createTinyUrl(@Valid @RequestBody TinyUrl tinyUrl) {

		TinyUrl obj = service.findByUrl(tinyUrl.getUrl());

		if (obj == null) {
			System.out.println("Object not found");
			obj = service.save(tinyUrl);
		}

		return obj;
	}

	@RequestMapping(value = "/listall/", method = RequestMethod.GET)
	public List<TinyUrl> getAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/url/{shortUrl}", method = RequestMethod.GET)
	public TinyUrl getUrl(@PathVariable("shortUrl") String shortUrl) {

		return service.findByShortUrl(shortUrl);
	}
}