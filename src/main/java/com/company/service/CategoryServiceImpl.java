package com.company.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		entity.setCreatedAt(new Date());
		Category saved = repository.save(entity);
		logger.info("El objeto se ha guardo");

		return CategoryMapper.INSTANCE.mapToCategoryDTO(saved);
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
		Optional<Category> category = repository.findById(id);
		return category.map(CategoryMapper.INSTANCE::mapToCategoryDTO);
	}

	@Override
	public CategoryDTO update(CategoryDTO dto, String id) {
		Category entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		CategoryMapper.INSTANCE.update(dto, entity);
		CategoryDTO categoryDTO = CategoryMapper.INSTANCE.mapToCategoryDTO(repository.save(entity));
		return categoryDTO;
	}

	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	}

	@Override
	public CategoryDTO partialUpdate(CategoryDTO dto, String id) {
		Category entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		CategoryMapper.INSTANCE.updatePatchEntity(dto, entity);
		CategoryDTO categoryDTO = CategoryMapper.INSTANCE.mapToCategoryDTO(repository.save(entity));
		return categoryDTO;
	}

	@Override
	public Page<CategoryDTO> getAllPaginated(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable paging = PageRequest.of(pageNo, pageSize, sort);
		Page<Category> pageEntities = repository.findAll(paging);
		Page<CategoryDTO> pag = pageEntities.map(obj -> CategoryMapper.INSTANCE.mapToCategoryDTO(obj));

		return pag;
	}

}
