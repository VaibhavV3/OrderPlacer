package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.example.DatabaseConnection;

@RestController
public class HelloWorldController {

    @Autowired
    private DatabaseConnection dbConnection;

    @GetMapping("/hello")
    public String hello() {
	dbConnection.testConnection();
        return "Hello World!";
    }
}
