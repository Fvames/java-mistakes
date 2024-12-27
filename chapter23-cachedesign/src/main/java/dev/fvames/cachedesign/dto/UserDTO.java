package dev.fvames.cachedesign.dto;

import dev.fvames.cachedesign.config.validate.InRedisList;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {
	private int id;
	@NotNull(message = "name不能为空")
	private String name;
	private String email;
	@NotNull
	@InRedisList(redisDatabase = "0", redisKey = "yx:orgIds")
	private String orgId;
}
