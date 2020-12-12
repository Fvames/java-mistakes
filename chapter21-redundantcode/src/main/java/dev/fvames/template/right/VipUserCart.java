package dev.fvames.template.right;

import dev.fvames.template.entity.Db;
import dev.fvames.template.entity.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * vpi 用户 计算规则
 *
 * @author
 * @version 2020/12/3 12:28
 */

@Service(value = "VipUserCart")
public class VipUserCart extends NormalUserCart {

	@Override
	protected void processCouponPrice(long userId, Item item) {
		if (item.getQuantity() > 2) {
			item.setCouponPrice(item.getPrice()
					.multiply(BigDecimal.valueOf(100 - Db.getUserCouponPercent(userId)).divide(new BigDecimal("100")))
					.multiply(BigDecimal.valueOf(item.getQuantity() - 2)));
		} else {
			item.setCouponPrice(BigDecimal.ZERO);
		}
	}
}
