package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.Role;
import com.company.repository.RoleRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/roles")
public class RoleController {
	
	@Autowired
	private RoleRepository repository;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Role role) {
		return ResponseEntity.ok(repository.save(role));
	}
	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody Role role) {
		Role r = repository.findById(id).get();
		r.setName(role.getName());
		
		return ResponseEntity.ok(repository.save(r));
	}
}
