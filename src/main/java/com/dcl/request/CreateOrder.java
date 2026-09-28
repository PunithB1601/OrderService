package com.dcl.request;

import lombok.Data;

@Data
public class CreateOrder {

	private Integer productId;
	
	private Integer quantity;
}
