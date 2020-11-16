package dev.fvames.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingApplication {

    public static void main(String[] args) {
        System.out.println(System.getenv());
        System.setProperty("logging.config",
                "chapter13-logging/src/main/java/dev/fvames/logging/duplicate/multiplelevelsfilter.xml");
        SpringApplication.run(LoggingApplication.class, args);
    }
}

