package dev.fvames.productionready.metrics;

import org.springframework.context.annotation.Profile;

@Profile("metrics")
public class Consts {
    public static final String QUEUE = "createOrder";
    public static final String EXCHANGE = "createOrder";
    public static final String ROUTING_KEY = "createOrder";
}
