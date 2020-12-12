package dev.fvames.exception.predefinedexception;

/**
 * TODO 类描述
 *
 * @author
 * @version 2020/12/3 10:26
 */

public class BusinessException extends RuntimeException{
	private int code;

	public BusinessException(String message, int code) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
