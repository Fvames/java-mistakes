package dev.fvames.productionready.info;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Profile("info")
@Endpoint(id = "adder")
@Component
public class TestEndpoint {
    private static AtomicLong atomicLong = new AtomicLong();

    @ReadOperation
    public String get() {
        return String.valueOf(atomicLong.get());
    }

    @WriteOperation
    public String increment() {
        return String.valueOf(atomicLong.incrementAndGet());
    }
}
