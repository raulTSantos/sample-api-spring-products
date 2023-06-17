package com.company.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.CategoryDTO;
import com.company.service.CategoryService;
import com.company.util.ApiConstants;
import com.company.util.ResponseHandler;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
	@Autowired
	private CategoryService service;

	/*@GetMapping
	public ResponseEntity<List<CategoryDTO>> retrieveAllData() {
		return ResponseEntity.ok(service.getAll());
	}*/

	@GetMapping
	public ResponseEntity<Page<CategoryDTO>> retrieveAllDataPaginated(
			@RequestParam(value = "page", defaultValue = ApiConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "size", defaultValue = ApiConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = ApiConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = ApiConstants.DEFAULT_SORT_DIRECT, required = false) String sortDir) {
		
		return ResponseEntity.ok(service.getAllPaginated(pageNo, pageSize, sortBy, sortDir));
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid CategoryDTO dto) {
		CategoryDTO category = service.save(dto);
		return ResponseHandler.success(ApiConstants.MSG_CREATE_SUCC, HttpStatus.OK, category);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody @Valid CategoryDTO dto) {
		CategoryDTO updated = service.update(dto, id);
		return ResponseHandler.success(ApiConstants.MSG_UPDATE_SUCC, HttpStatus.OK, updated);
	}

	@PatchMapping("{id}")
	public ResponseEntity<?> partial(@PathVariable("id") String id, @RequestBody CategoryDTO dto) {
		CategoryDTO updated = service.partialUpdate(dto, id);
		return ResponseHandler.success(ApiConstants.MSG_UPDATE_SUCC, HttpStatus.OK, updated);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		service.deleteById(id);
		return ResponseHandler.success(ApiConstants.MSG_DELETE_SUCC, HttpStatus.NO_CONTENT, null);
	}
}
