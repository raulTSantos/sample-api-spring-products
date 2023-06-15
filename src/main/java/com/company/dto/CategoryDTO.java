package com.company.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

	private String id;

	@NotBlank(message = "Ingrese un nombre valido.")
	@NotEmpty(message = "este es un vacio")
	private String name;
}
