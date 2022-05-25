package com.kushal.blog.services;

import java.util.List;

import com.kushal.blog.payloads.CategoryDto;

public interface CategoryService {
	public CategoryDto createCategory(CategoryDto categoryDto);

	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	public void deleteCategory(Integer categoryId);

	public CategoryDto getCategoryById(Integer categoryId);

	public List<CategoryDto> getAllCategory();
}
