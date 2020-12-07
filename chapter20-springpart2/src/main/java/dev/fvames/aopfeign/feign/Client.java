package dev.fvames.aopfeign.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "client")
public interface Client {
    @GetMapping("/feignaop/server")
    String api();
}
