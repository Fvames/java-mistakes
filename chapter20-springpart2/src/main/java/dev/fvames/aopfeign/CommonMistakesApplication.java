package dev.fvames.aopfeign;

import dev.fvames.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class CommonMistakesApplication {

    public static void main(String[] args) {
		PropertiesUtils.loadPropertySource(CommonMistakesApplication.class, "feign.properties");
        SpringApplication.run(CommonMistakesApplication.class, args);
    }

}

