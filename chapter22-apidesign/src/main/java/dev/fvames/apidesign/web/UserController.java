package dev.fvames.apidesign.web;

import dev.fvames.apidesign.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/user")
public class UserController {

	public static final Map<String, UserVO> cacheUser = new ConcurrentHashMap<>();

	@GetMapping(value = "/get")
	public UserVO getById(@RequestParam("id") String id) {

		return cacheUser.get(id);
	}

	@PostMapping(value = "/post")
	public String saveUser(@RequestBody UserVO userVO) {

		cacheUser.put(userVO.getId(), userVO);
		return "success";
	}


}
