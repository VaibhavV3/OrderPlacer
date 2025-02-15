package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import org.example.DatabaseConnection;

@RestController
public class HelloWorldController {

    @Autowired
    private DatabaseConnection dbConnection;

    private static final Logger logger = LogManager.getLogger(HelloWorldController.class);


    @GetMapping("/hello")
    public String hello() {
        System.out.println("Working Directory: " + System.getProperty("user.dir"));
	dbConnection.testConnection();
	logger.info("returning nothing over here");
        return "Hello World!";
    }
}
