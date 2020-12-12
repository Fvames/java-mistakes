package dev.fvames.template.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品
 *
 * @author
 * @version 2020/12/3 11:25
 */

@Data
public class Item {
	//商品Id
	private long id;
	//商品数量
	private int quantity;
	//商品单价
	private BigDecimal price;
	//商品优惠
	private BigDecimal couponPrice;
	//商品运费
	private BigDecimal deliveryPrice;
}