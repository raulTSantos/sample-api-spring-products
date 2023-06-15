package com.company.service;

import java.util.List;
import java.util.Optional;

import com.company.dto.CategoryDTO;

public interface CategoryService {
	public CategoryDTO save(CategoryDTO dto);
	public List<CategoryDTO> getAll();
	public Optional<CategoryDTO> findEntity(String id);
}
