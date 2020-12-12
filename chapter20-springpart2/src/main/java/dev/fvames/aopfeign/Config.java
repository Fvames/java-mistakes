package dev.fvames.aopfeign;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "dev.fvames.aopfeign.feign")
public class Config {
}
