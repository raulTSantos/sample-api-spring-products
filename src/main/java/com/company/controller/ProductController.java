package com.company.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.ProductDTO;
import com.company.model.Product;
import com.company.service.ProductService;
import com.company.util.ApiConstants;
import com.company.util.ResponseHandler;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/products")
public class ProductController {
	@Autowired
	private ProductService service;

	@GetMapping("/all")
	public ResponseEntity<List<Product>> retrieveAllData() {
		return ResponseEntity.ok(service.getAll());
	}
	@GetMapping
	public ResponseEntity<List<Product>> retrieveAllEnabled() {
		return ResponseEntity.ok(service.findByEnabled());
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid Product obj) {
		Product saved = service.save(obj);
		return ResponseHandler.success(ApiConstants.MSG_CREATE_SUCC, HttpStatus.OK, saved);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody @Valid ProductDTO dto) {
		ProductDTO updated = service.update(dto, id);
		return ResponseHandler.success(ApiConstants.MSG_UPDATE_SUCC, HttpStatus.OK, updated);
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<?> partial(@PathVariable("id") String id, @RequestBody ProductDTO dto) {
		service.partialUpdate(dto, id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
