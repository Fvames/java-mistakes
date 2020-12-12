package dev.fvames.template.right;

import dev.fvames.template.entity.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 内部用户 计算规则
 *
 * @author
 * @version 2020/12/3 12:26
 */

@Service(value = "InternalUserCart")
public class InternalUserCart extends AbstractCart {
	@Override
	protected void processCouponPrice(long userId, Item item) {
		item.setCouponPrice(BigDecimal.ZERO);
	}

	@Override
	protected void processDeliveryPrice(long userId, Item item) {
		item.setDeliveryPrice(BigDecimal.ZERO);
	}
}