package dev.fvames.cachedesign.controller;

import dev.fvames.cachedesign.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * validate in redis 测试验证
 */
@Slf4j
@RestController
public class MyValidateController {

	@Autowired
	private RedisTemplate<String, String> redisTemplate1;

	@GetMapping("/validate")
	public UserDTO validate(@Validated @ModelAttribute UserDTO user) {
		log.info("user:" + user);
		return user;
	}
}
