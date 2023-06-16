package com.company.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDTO {

	private String id;

	@NotBlank(message = "Ingrese un nombre valido.")
	private String name;

	private Boolean enabled;
	
	private Date createdAt;
	

}
