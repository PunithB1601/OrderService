package com.dcl.dto;

import lombok.Data;

@Data
public class OrderDto {

	private Integer orderId;
	
	private ProductDto pdto;
	
	private Integer quantity;
	
	private Double totalPrice;
	
}
