package com.company.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.company.dto.CategoryDTO;
import com.company.model.Category;

@Mapper
public interface CategoryMapper {
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	@Mapping(source = "name", target = "name", qualifiedByName = "convertToUpperCase")
	Category mapToCategory(CategoryDTO dto);

	CategoryDTO mapToCategoryDTO(Category entity);

	@Named("convertToUpperCase")
	public static String convertToUpperCase(String value) {
		return value.toUpperCase();
	}
	
	@Mapping(target = "id", ignore = true)
	@InheritConfiguration(name ="mapToCategory" )
	Category update(CategoryDTO update, @MappingTarget Category destination);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updatePatchEntity(CategoryDTO dto, @MappingTarget Category entity);
}
