package dev.fvames.connectionpool.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@Slf4j
@Configuration
@ConditionalOnClass({RestTemplate.class, HttpClient.class})
@EnableConfigurationProperties({RestTemplateProperties.class})

public class RestTemplateConfig {
    

    private final RestTemplateProperties properties;

    public RestTemplateConfig(RestTemplateProperties properties) {
        this.properties = properties;
        if(this.properties.getMaxTotal() == 0){
            this.properties.setMaxTotal(50);
        }

        if(this.properties.getDefaultMaxPerRoute() == 0){
            this.properties.setDefaultMaxPerRoute(10);
        }

        if(this.properties.getConnectionRequestTimeout() == 0){
            this.properties.setConnectionRequestTimeout(5000);
        }

        if(this.properties.getConnectTimeout() == 0){
            this.properties.setConnectTimeout(5000);
        }

        if(this.properties.getReadTimeout() == 0){
            this.properties.setReadTimeout(5000);
        }
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        log.debug("create bean of org.springframework.web.client.RestTemplate");
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		for (HttpMessageConverter<?> messageConverter : messageConverters) {
			if (messageConverter instanceof StringHttpMessageConverter) {
				((StringHttpMessageConverter) messageConverter).setDefaultCharset(StandardCharsets.UTF_8);
				break;
			}
		}

		return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() throws NoSuchAlgorithmException
            , KeyStoreException, KeyManagementException{
        log.debug("create bean of org.springframework.http.client.ClientHttpRequestFactory with:");
        log.debug("max-total: {}", properties.getMaxTotal());
        log.debug("default-max-per-route: {}", properties.getDefaultMaxPerRoute());
        log.debug("connect-timeout: {}", properties.getConnectTimeout());
        log.debug("read-timeout: {}", properties.getReadTimeout());
        log.debug("connection-request-timeout: {}", properties.getConnectionRequestTimeout());
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1)->true).build();
        httpClientBuilder.setSSLContext(sslContext);
        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                hostnameVerifier);
        // 注册http和https请求
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslConnectionSocketFactory).build();
        // 开始设置连接池
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager =
                new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        // 最大连接数300
        poolingHttpClientConnectionManager.setMaxTotal(properties.getMaxTotal());
        // 同路由并发数50
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(properties.getDefaultMaxPerRoute());
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);

        // httpClient连接配置
        HttpClient httpClient = httpClientBuilder.build();
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);
        // 连接超时
        clientHttpRequestFactory.setConnectTimeout(properties.getConnectTimeout());
        // 数据读取超时时间
        clientHttpRequestFactory.setReadTimeout(properties.getReadTimeout());
        // 连接不够用的等待时间
        clientHttpRequestFactory.setConnectionRequestTimeout(properties.getConnectionRequestTimeout());
        return clientHttpRequestFactory;
    }

}
