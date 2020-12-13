package dev.fvames.productionready;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProductReadyApplication {

    public static void main(String[] args) {
        //Utils.loadPropertySource(ProductReadyApplication.class, "actuator.properties");

        SpringApplication.run(ProductReadyApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

