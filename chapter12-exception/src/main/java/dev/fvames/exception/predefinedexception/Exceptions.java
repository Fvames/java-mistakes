package dev.fvames.exception.predefinedexception;

/**
 * TODO 类描述
 *
 * @author
 * @version 2020/12/3 10:26
 */

public class Exceptions {
	public static BusinessException ORDEREXISTS = new BusinessException("订单已经存在", 3001);

	public static BusinessException orderExists() {
		return new BusinessException("订单已经存在", 3001);
	}
}
