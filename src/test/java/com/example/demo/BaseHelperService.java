package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BaseHelperService {
    private Environment environment;

    public String getBasePath(){
        final var key = environment.getProperty("local.server.port");
        return "http://localhost:"+key;
    }
}
