package com.tk.app.tinyurl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tk.app.tinyurl.util.Base62Util;

@Service
@CacheConfig(cacheNames = "tinyUrlCache")
public class TinyUrlService {

	@Autowired
	private TinyUrlRepository repository;

	@Autowired
	Base62Util converter;

	@CachePut(key = "#tinyUrl.url")
	public TinyUrl save(TinyUrl tinyUrl) {
		System.out.println("Called : save");

		String shortKey = converter.generateShortKey();
		/*
		 * while (this.findByShortUrl(shortKey) != null) { shortKey =
		 * converter.generateShortKey();
		 * System.out.println("Duplicate shortkey found, generating new"); }
		 */
		tinyUrl.setShortUrl(shortKey);
		return repository.save(tinyUrl);

	}

	@Cacheable("tinyUrlCache")
	public List<TinyUrl> findAll() {
		return repository.findAll();
	}

	@Cacheable(key = "#root.args[0]", unless = "#result == null")
	public TinyUrl findByShortUrl(String shortUrl) {
		System.out.println("Called : findByShortUrl");
		return repository.findByShortUrl(shortUrl);
	}

	@Cacheable(key = "#root.args[0]", unless = "#result == null")
	public TinyUrl findByUrl(String url) {
		System.out.println("Called : findByUrl");
		return repository.findByUrl(url);
	}

}