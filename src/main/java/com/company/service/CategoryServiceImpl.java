package com.company.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.dto.CategoryDTO;
import com.company.mapper.CategoryMapper;
import com.company.model.Category;
import com.company.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryRepository repository;

	@Override
	public CategoryDTO save(CategoryDTO dto) {

		Category entity = CategoryMapper.INSTANCE.mapToCategory(dto);
		Category saved = repository.save(entity);
		CategoryDTO categoryDTO = CategoryMapper.INSTANCE.mapToCategoryDTO(saved);
		logger.info("El objeto se ha guardo");

		return categoryDTO;
	}

	@Override
	public List<CategoryDTO> getAll() {
		List<Category> listEntities = repository.findAll();

		List<CategoryDTO> listDtos = listEntities.stream().map(CategoryMapper.INSTANCE::mapToCategoryDTO)
				.collect(Collectors.toList());

		return listDtos;
	}

	@Override
	public Optional<CategoryDTO> findEntity(String id) {
		
		return Optional.empty();
	}

}
