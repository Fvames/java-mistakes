package dev.fvames.config.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = { InRedisValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface InRedis {

	String message() default "ID is not valid in the Redis cache";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	// 用于区分不同的校验规则
	String redisDatabase() default "";
	String redisKey() default "";
}
