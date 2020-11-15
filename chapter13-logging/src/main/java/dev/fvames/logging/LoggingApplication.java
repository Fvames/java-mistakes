package dev.fvames.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingApplication {

    public static void main(String[] args) {
        System.setProperty("logging.config", "classpath:dev/fvames/logging/async/performance_async.xml");
        SpringApplication.run(LoggingApplication.class, args);
    }
}

