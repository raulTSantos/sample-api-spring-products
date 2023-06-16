package com.company.dto;

import lombok.Data;

@Data
public class ProductDTO {
	private String name;
	private Integer stock;
	private String description;
	private String image;
	private Double price;
	private Boolean enabled;
	private String categoryId;
}
