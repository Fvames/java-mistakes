package dev.fvames.connectionpool.resttemplate;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rest-template")
public class RestTemplateProperties {
    /**
     * 最大并发访问
     */
    private int maxTotal;

    /**
     * 默认最大同路由并发访问
     */
    private int defaultMaxPerRoute;

    /**
     * Connection timeout(ms)
     */
    private int connectTimeout;

    /**
     * Response timeout(ms)
     */
    private int readTimeout;

    /**
     * Request timeout(ms)
     */
    private int connectionRequestTimeout;

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

}
