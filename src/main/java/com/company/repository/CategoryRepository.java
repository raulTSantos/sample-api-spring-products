package com.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

}
