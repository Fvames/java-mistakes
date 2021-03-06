package dev.fvames.apidesign;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * TODO 类描述
 *
 * @author
 * @version 2020/12/7 15:29
 */
@Slf4j
public class Utils {

	public static void loadPropertySource(Class clazz, String fileName) {
		try {
			Properties p = new Properties();
			p.load(clazz.getResourceAsStream(fileName));
			p.forEach((k, v) -> {
				log.info("{}={}", k, v);
				System.setProperty(k.toString(), v.toString());
			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
