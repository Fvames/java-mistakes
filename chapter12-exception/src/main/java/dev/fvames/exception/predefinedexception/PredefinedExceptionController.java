package dev.fvames.exception.predefinedexception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 静态异常 堆栈信息有误（行号不正确）
 *
 * @author
 * @version 2020/12/3 10:28
 */
@RestController
@Slf4j
@RequestMapping("predefinedexception")
public class PredefinedExceptionController {

	@GetMapping("wrong")
	public void wrong() {
		try {
			createOrderWrong();
		} catch (Exception ex) {
			log.error("createOrder got error", ex);
		}
		try {
			cancelOrderWrong();
		} catch (Exception ex) {
			log.error("cancelOrder got error", ex);
		}
	}

	@GetMapping("right")
	public void right() {

		try {
			createOrderRight();
		} catch (Exception ex) {
			log.error("createOrder got error", ex);
		}
		try {
			cancelOrderRight();
		} catch (Exception ex) {
			log.error("cancelOrder got error", ex);
		}
	}

	private void createOrderWrong() {
		throw Exceptions.ORDEREXISTS;
	}

	private void cancelOrderWrong() {
		throw Exceptions.ORDEREXISTS;
	}

	private void createOrderRight() {
		throw Exceptions.orderExists();
	}

	private void cancelOrderRight() {
		throw Exceptions.orderExists();
	}
}
