package com.example.dana.category.service;

import com.example.dana.category.controller.request.CategoryRequest;
import com.example.dana.category.controller.response.CategoryResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface CategoryService {
    CategoryResponse addParentCategory(CategoryRequest request);

    List<CategoryResponse> addChildrenCategories(Long parentId, List<CategoryRequest> requests);

    CategoryResponse updateCategory(Long categoryId, CategoryRequest request);

    CategoryResponse deleteCategory(Long categoryId);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryByCategoryId(Long categoryId);

    List<CategoryResponse> getChildrenCategoriesByParentId(Long parentId);
}
