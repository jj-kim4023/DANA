package com.example.dana.category.service;

import com.example.dana.category.controller.request.CategoryRequest;
import com.example.dana.category.controller.response.CategoryResponse;
import com.example.dana.category.domain.entity.Category;
import com.example.dana.category.domain.repository.CategoryRepository;
import com.example.dana.common.exception.UserHandleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.dana.category.constants.CategoryErrorMessage.NOT_FOUND_CATEGORY_EXCEPTION;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public CategoryResponse addParentCategory(CategoryRequest request) {
        Category savedCategory = categoryRepository.save(Category.fromRequest(request));
        return CategoryResponse.fromEntity(savedCategory);
    }

    @Transactional
    @Override
    public CategoryResponse addChildrenCategories(Long parentId, List<CategoryRequest> requests) {
        Category parentCategory = findByCategoryId(parentId);

        validateActivation(parentCategory);

        List<Category> childCategories = requests.stream()
                .map(Category::fromRequest)
                .collect(Collectors.toList());
        parentCategory.addChildrenCategories(childCategories);
        Category savedCategory = categoryRepository.save(parentCategory);
        return CategoryResponse.fromEntity(savedCategory);
    }

    @Transactional
    @Override
    public CategoryResponse updateCategory(Long categoryId, CategoryRequest request) {
        return CategoryResponse.fromEntity(categoryRepository.findById(categoryId).orElseThrow());
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return null;
    }

    @Override
    public CategoryResponse getCategoriesByCategoryId(Long categoryId) {
        return CategoryResponse.fromEntity(categoryRepository.findById(categoryId).orElseThrow());    }

    @Override
    public List<CategoryResponse> getChildrenCategoriesByParentId(Long parentId) {
        return null;
    }

    private void validateActivation(Category parentCategory) {
    }

    private Category findByCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new UserHandleException(NOT_FOUND_CATEGORY_EXCEPTION));
    }
}
