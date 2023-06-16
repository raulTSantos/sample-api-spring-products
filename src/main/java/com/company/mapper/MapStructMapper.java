package com.company.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.company.dto.ProductDTO;
import com.company.model.Product;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapStructMapper {
	/*@Mapping(ignore = true, target = "id")
	@Mapping(ignore = true, target = "createdAt")*/
	@Mapping(source = "categoryId",target = "category.id")
	Product mapToProduct(ProductDTO dto);
	
	@Mapping(source = "category.id", target = "categoryId")
	ProductDTO mapToProductDto(Product entity);
	

	@Mapping(source = "categoryId",target = "category.id")
	void update(ProductDTO update, @MappingTarget Product destination);
	
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(source = "categoryId",target = "category.id")
	void updatePatchEntity(ProductDTO dto, @MappingTarget Product entity);
}
