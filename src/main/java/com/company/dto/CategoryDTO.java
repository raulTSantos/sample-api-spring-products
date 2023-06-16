package com.company.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDTO {

	private String id;

	@NotBlank(message = "Ingrese un nombre valido.")
	@NotEmpty(message = "este es un vacio")
	private String name;

	private Boolean enabled;
	
	private Date createdAt;
	

}
