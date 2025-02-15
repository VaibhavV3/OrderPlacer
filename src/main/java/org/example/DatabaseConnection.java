package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component // Makes this a Spring-managed bean
public class DatabaseConnection {

    @Value("${spring.datasource.url}") // Inject from application.properties or application.yml
    private String dbUrl;

    @Value("${spring.datasource.username}") // Inject from application.properties or application.yml
    private String dbUsername;

    @Value("${spring.datasource.password}") // Inject from application.properties or application.yml
    private String dbPassword;

    public Connection getConnection() throws SQLException {
        try {
	    System.out.println(dbUrl+" "+dbUsername);
            // Class.forName("org.postgresql.Driver");  // Optional: Explicitly load the driver (usually not needed)
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            System.err.println("Error connecting to PostgreSQL database: " + e.getMessage());
            throw e; // Re-throw the exception so calling methods are aware of the failure
        }
    }


    // Example usage (you would typically use this in a service class or repository)
    public void testConnection() {
        try (Connection connection = getConnection()) {
            System.out.println("Successfully connected to PostgreSQL RDS!");
            // Perform database operations here (e.g., create statements, execute queries)
        } catch (SQLException e) {
            System.err.println("Error in testConnection: " + e.getMessage());
        }
    }
}
