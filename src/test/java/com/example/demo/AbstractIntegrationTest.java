package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class AbstractIntegrationTest {
    @Autowired
    BaseHelperService baseHelperService;
    @Autowired
    DataBaseCleanerService dataBaseCleanerService;

    @BeforeEach
    void init(){
        this.dataBaseCleanerService.clean();
    }


    public String baseUrl(){
        return baseHelperService.getBasePath();
    }

}
