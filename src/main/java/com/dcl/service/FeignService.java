package com.dcl.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dcl.response.ApiResponse;

@FeignClient(name = "PRODUCT-SERVICE", path="/product")
public interface FeignService {

	@GetMapping("/get")
	public ApiResponse getAllProducts();
	
	@GetMapping("/get/{productId}")
	public ApiResponse getProductById(@PathVariable Integer productId);
	
}
