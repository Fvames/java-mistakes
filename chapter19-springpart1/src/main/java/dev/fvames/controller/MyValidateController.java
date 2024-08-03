package dev.fvames.controller;

import dev.fvames.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
