package dev.fvames.apidesign.web;

import dev.fvames.apidesign.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	public static final Map<String, UserVO> cacheUser = new ConcurrentHashMap<>();

	@GetMapping(value = "/get")
	public UserVO getById(@RequestParam("id") String id) {
		log.info("获取用户：[{}] 的数据", id);
		return cacheUser.getOrDefault(id, new UserVO());
	}

	@PostMapping(value = "/post")
	public String saveUser(@RequestBody UserVO userVO) {

		cacheUser.put(userVO.getId(), userVO);
		return "success";
	}


}
