package dev.fvames.config.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class InRedisValidator implements ConstraintValidator<InRedis, String> {

	@Autowired
	private ConfigurableApplicationContext context;

	private String redisDatabase;
	private String redisKey;

	@Override
	public void initialize(InRedis constraintAnnotation) {
		this.redisDatabase = constraintAnnotation.redisDatabase();
		this.redisKey = constraintAnnotation.redisKey();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isEmpty(value)) {
			return false; // 可以根据需求选择返回 false 或者 true
		}
		RedisTemplate<String, String> redisTemplate = this.context.getBean("redisTemplate" + redisDatabase, RedisTemplate.class);
		// 根据不同的 redisDatabase 执行不同的逻辑
		ListOperations<String, String> listOperations = redisTemplate.opsForList();
		List<String> redisResult = listOperations.range(redisKey, 0, -1);
		return redisResult.contains(Integer.valueOf(value));
	}
}
