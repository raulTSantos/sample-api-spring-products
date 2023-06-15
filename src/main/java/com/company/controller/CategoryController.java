package com.company.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.CategoryDTO;
import com.company.service.CategoryService;
import com.company.util.ApiConstans;
import com.company.util.ResponseHandler;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> retrieveAllData() {

		return ResponseEntity.ok(service.getAll());
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid CategoryDTO dto) {
		return ResponseHandler.success(ApiConstans.MSG_CREATE_SUCC, HttpStatus.OK, service.save(dto));
	}
}
