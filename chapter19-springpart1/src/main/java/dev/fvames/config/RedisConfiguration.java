package dev.fvames.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

@Configuration
@SuppressWarnings("unused")
public class RedisConfiguration {

	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.database0}")
	private int database0;
	@Value("${spring.redis.database1}")
	private int database1;
	@Value("${spring.redis.database2}")
	private int database2;

	public RedisConnectionFactory getFactory(int databaseNum) {
		// 构建工厂对象
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName(host);
		configuration.setPort(port);
		// configuration.setPassword(RedisPassword.of(password));
		// LettucePoolingClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder()
		// 		.commandTimeout(Duration.ofSeconds(timeout)).poolConfig(getPoolConfig()).build();
		LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration);
		// 设置使用的redis数据库
		factory.setDatabase(databaseNum);
		// 重新初始化工厂
		factory.afterPropertiesSet();
		return factory;
	}

	@Bean(name = "redisTemplate0")
	public RedisTemplate<String, Object> redisTemplate0(){
		RedisTemplate<String, Object> template = getStringObjectRedisTemplate(database0);
		return template;
	}

	private RedisTemplate<String, Object> getStringObjectRedisTemplate(int database) {
		RedisConnectionFactory factory = getFactory(database);

		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(RedisSerializer.string());
		template.setValueSerializer(RedisSerializer.json());
		template.setHashKeySerializer(RedisSerializer.string());
		template.setHashValueSerializer(RedisSerializer.json());
		return template;
	}

	@Bean(name = "redisTemplate1")
	public RedisTemplate<String, Object> redisTemplate1(){
		RedisTemplate<String, Object> template = getStringObjectRedisTemplate(database1);
		return template;
	}

	@Bean(name = "redisTemplate2")
	public RedisTemplate<String, Object> redisTemplate2(){
		RedisTemplate<String, Object> template = getStringObjectRedisTemplate(database2);
		return template;
	}

}
