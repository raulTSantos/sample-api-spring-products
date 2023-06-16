package com.company.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.company.dto.CategoryDTO;

public interface CategoryService {
	public CategoryDTO save(CategoryDTO dto);

	public List<CategoryDTO> getAll();

	public Optional<CategoryDTO> findEntity(String id);

	public CategoryDTO update(CategoryDTO dto,String id);
	
	public CategoryDTO partialUpdate(CategoryDTO dto,String id);
	
	public Page<CategoryDTO> getAllPaginated(int pageNo, int pageSize, String sortBy, String sortDir);

	public void deleteById(String id);
}
