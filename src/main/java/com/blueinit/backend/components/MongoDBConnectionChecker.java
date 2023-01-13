package com.blueinit.backend.components;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.boot.CommandLineRunner;

public class MongoDBConnectionChecker implements CommandLineRunner {


        private MongoTemplate mongoTemplate;
        @Override
        @PostConstruct
        public void run(String... args) throws Exception {
            System.out.println("Running MongoDBConnectionChecker");

            try {
                mongoTemplate.executeCommand("{ ping: 1 }");
                System.out.println("Successfully connected to MongoDB server");
            } catch (Exception e) {
                System.out.println("Error connecting to MongoDB: " + e.getMessage());
            }
            System.out.println("Successfully connected to MongoDB server");
            mongoTemplate.executeCommand("{ buildInfo: 1 }");
            System.out.println("MongoDB Connection Checker End!");
        }


}
