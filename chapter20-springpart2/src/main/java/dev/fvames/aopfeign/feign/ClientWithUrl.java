package dev.fvames.aopfeign.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "anotherClient", url = "http://localhost:45678")
public interface ClientWithUrl {
    @GetMapping("/feignaop/server")
    String api();
}
