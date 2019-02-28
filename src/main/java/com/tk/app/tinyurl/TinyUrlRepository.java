package com.tk.app.tinyurl;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TinyUrlRepository extends MongoRepository<TinyUrl, Long> {	
        
    public TinyUrl findByShortUrl(String shortUrl);
    
    public TinyUrl findByUrl(String url);
           

}