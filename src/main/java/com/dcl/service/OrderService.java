package com.dcl.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcl.dto.OrderDto;
import com.dcl.dto.ProductDto;
import com.dcl.entity.Orders;
import com.dcl.repo.OrdersRepo;
import com.dcl.request.CreateOrder;
import com.dcl.response.ApiResponse;

@Service
public class OrderService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private FeignService fservice;
	
	@Autowired
	private OrdersRepo orepo;
	
	public List<ProductDto> getAllProducts(){
		ApiResponse<?> response=fservice.getAllProducts();
		List<ProductDto> dtoList=(List<ProductDto>)response.getData();
		return dtoList;
	}
	
	public OrderDto orderProduct(CreateOrder order) {
		
		Orders o=new Orders(); 
		o.setProductId(order.getProductId());
		o.setQuantity(order.getQuantity());
	//	System.out.println(order.getProductId());
		Integer productId=order.getProductId();
		ApiResponse apiResponse=fservice.getProductById(productId);
		Map<String, Object> productMap =(LinkedHashMap<String, Object>)apiResponse.getData();
		ProductDto pdto=new ProductDto();
		pdto.setBrand((String)productMap.get("brand"));
		pdto.setProductId((Integer)productMap.get("productId"));
		pdto.setProductName((String)productMap.get("productName"));
		pdto.setPrice((Double)productMap.get("price"));
		
		
		
		Double total=pdto.getPrice()*order.getQuantity();
		o.setTotalPrice(total);
		o=orepo.save(o);
		OrderDto dto=mapper.map(o, OrderDto.class);
		dto.setPdto(pdto);
		return dto;
	}
	
}
