package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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

    private static final Logger logger = LogManager.getLogger(DatabaseConnection.class);
    
    public Connection getConnection() throws SQLException {
        try {
	    logger.debug(dbUrl+" "+dbUsername);
            // Class.forName("org.postgresql.Driver");  // Optional: Explicitly load the driver (usually not needed)
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            logger.error("Error connecting to PostgreSQL database: " + e.getMessage());
            throw e; // Re-throw the exception so calling methods are aware of the failure
        }
    }


    // Example usage (you would typically use this in a service class or repository)
    public void testConnection() {
        try (Connection connection = getConnection()) {
            logger.info("Successfully connected to PostgreSQL RDS!");
            // Perform database operations here (e.g., create statements, execute queries)
        } catch (SQLException e) {
            logger.error("Error in testConnection: " + e.getMessage());
        }
    }
}
