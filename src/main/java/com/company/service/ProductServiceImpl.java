package com.company.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.dto.ProductDTO;
import com.company.mapper.MapStructMapper;
import com.company.model.Product;
import com.company.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository repository;
	
	MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

	@Override
	public Product save(Product obj) {
		if (obj.getEnabled() == null) {
			obj.setEnabled(true);
		}
		obj.setCreatedAt(new Date());
		return repository.save(obj);
	}

	@Override
	public void deleteById(String id) {
		Optional<Product> p = repository.findById(id);
		if (p.isEmpty()) {
			throw new EntityNotFoundException("No existe el ID que se desea eliminar.");
		}
		repository.deleteById(id);
	}

	@Override
	public List<Product> getAll() {
		return repository.findAll();
	}

	@Override
	public ProductDTO update(ProductDTO dto, String id) {
		Product entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe el ID que desa actualizar"));
		INSTANCE.update(dto, entity);
		ProductDTO  updated= INSTANCE.mapToProductDto(repository.save(entity));
		return updated;
	}

	@Override
	public void partialUpdate(ProductDTO dto, String id) {
		Product entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe el ID que desa actualizar"));
		INSTANCE.updatePatchEntity(dto, entity);
		repository.save(entity);
	}

	@Override
	public List<Product> findByEnabled() {	
		return repository.findByEnabled(true);
	}

}
