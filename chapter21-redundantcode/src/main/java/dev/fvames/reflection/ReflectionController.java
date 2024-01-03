package dev.fvames.reflection;

import dev.fvames.reflection.right.BetterBankService;
import dev.fvames.reflection.wrong.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;


@Slf4j
@RestController
@RequestMapping("reflection")
public class ReflectionController {

	@Autowired
	private Environment environment;

	@PostConstruct
	public void init() {
		String requestPrefix = environment.getProperty("request.prefix");
		if (StringUtils.isEmpty(requestPrefix)) {
			log.error(">>没有配置请求地址前缀.");
		}
		BetterBankService.requestPrefix = requestPrefix;
	}

	@PostMapping("/bank/createUser")
	public String createUser(@RequestBody String data) {
		log.info(">> createUser called with argument {}", data);
		return "1";
	}

	@PostMapping("/bank/pay")
	public String pay(@RequestBody String data) {
		log.info("pay called with argument {}", data);
		return "OK";
	}

	@GetMapping("wrong")
	public void wrong() throws IOException {
		BankService.createUser("zhuye", "xxxxxxxxxxxxxxxxxx", "13612345678", 36);
		BankService.pay(1234L, new BigDecimal("100.5"));

	}

	@GetMapping("right")
	public void right() throws IOException {
		BetterBankService.createUser("zhuye", "xxxxxxxxxxxxxxxxxx", "13612345678", 36);
		BetterBankService.pay(1234L, new BigDecimal("100.5"));
	}
}