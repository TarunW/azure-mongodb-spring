package com.tk.app.tinyurl;

import java.io.Serializable;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TinyURLDocumnet")
/*@CompoundIndexes({
    @CompoundIndex(name = "tinyurl", def = "{'url' : 1, 'shortUrl': 1}")
})*/
public class TinyUrl implements Serializable {

	@Indexed(unique=true)
	private String url;

	@Indexed(unique=true)
	private String shortUrl;

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}