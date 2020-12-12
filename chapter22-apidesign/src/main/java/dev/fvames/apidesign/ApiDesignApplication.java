package dev.fvames.apidesign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiDesignApplication {

    public static void main(String[] args) {
        //Utils.loadPropertySource(ApiDesignApplication.class, "config.properties");
        SpringApplication.run(ApiDesignApplication.class, args);
    }
}

