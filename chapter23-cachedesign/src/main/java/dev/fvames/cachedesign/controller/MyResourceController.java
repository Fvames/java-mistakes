package dev.fvames.cachedesign.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 国际化
 */
@Slf4j
@RestController
public class MyResourceController {

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/source")
	public String printWelcomeMessage(String locale) {
		String welcomeMsg = messageSource.getMessage("name", null, LocaleContextHolder.getLocale());
		log.info(welcomeMsg);
		return welcomeMsg;
	}

}
