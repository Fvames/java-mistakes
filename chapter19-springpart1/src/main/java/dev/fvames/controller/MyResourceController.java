package dev.fvames.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class MyResourceController {

	@Autowired
	private MessageSource messageSource;

	@RequestMapping("/source")
	public String printWelcomeMessage(String locale) {
		String welcomeMsg = messageSource.getMessage("name", null, LocaleContextHolder.getLocale());
		System.out.println(welcomeMsg);
		return welcomeMsg;
	}

}
