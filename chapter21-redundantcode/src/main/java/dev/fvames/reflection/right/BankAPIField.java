package dev.fvames.reflection.right;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 银行接口 字段注解
 *
 * @author
 * @version 2020/12/3 17:02
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface BankAPIField {
	int order() default -1;

	int length() default -1;

	String type() default "";
}

