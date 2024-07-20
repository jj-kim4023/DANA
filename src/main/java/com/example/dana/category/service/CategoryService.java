package com.example.dana.category.service;

import com.example.dana.category.controller.request.CategoryRequest;
import com.example.dana.category.controller.response.CategoryResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {
    @Transactional
    CategoryResponse addParentCategory(CategoryRequest request);

    CategoryResponse addChildrenCategories(Long parentId, List<CategoryRequest> requests);
    CategoryResponse updateCategory(Long categoryId, CategoryRequest request);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoriesByCategoryId(Long categoryId);

    List<CategoryResponse> getChildrenCategoriesByParentId(Long parentId);
}
