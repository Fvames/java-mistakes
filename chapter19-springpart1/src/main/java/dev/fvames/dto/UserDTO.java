package dev.fvames.dto;

import dev.fvames.config.validate.InRedis;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {
	private int id;
	@NotNull(message = "name不能为空")
	private String name;
	private String email;
	@NotNull
	@InRedis(redisDatabase = "0", redisKey = "yx:orgIds")
	private String orgId;
}
