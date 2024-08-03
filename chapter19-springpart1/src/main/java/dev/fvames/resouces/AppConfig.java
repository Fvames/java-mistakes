package dev.fvames.resouces;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class AppConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		// messageSource.setBasename("file:///D:/code/java/java-mistakes/chapter19-springpart1/src/main/resources/messages_zh_CN.properties");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(2); // 设置为0以禁用缓存，实时加载
		return messageSource;
	}
}