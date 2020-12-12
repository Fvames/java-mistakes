package dev.fvames.template.right;

import dev.fvames.template.entity.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 普通用户 计算规则
 *
 * @author
 * @version 2020/12/3 12:27
 */

@Service(value = "NormalUserCart")
public class NormalUserCart extends AbstractCart {

	@Override
	protected void processCouponPrice(long userId, Item item) {
		item.setCouponPrice(BigDecimal.ZERO);
	}

	@Override
	protected void processDeliveryPrice(long userId, Item item) {
		item.setDeliveryPrice(item.getPrice()
				.multiply(BigDecimal.valueOf(item.getQuantity()))
				.multiply(new BigDecimal("0.1")));
	}
}