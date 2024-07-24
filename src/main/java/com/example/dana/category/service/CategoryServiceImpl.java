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
    public List<CategoryResponse> addChildrenCategories(Long parentId, List<CategoryRequest> requests) {
        Category parentCategory = findByCategoryId(parentId);

        /** 직접적으로 활성화 상태를 검사*/
        if (!parentCategory.isActive()) {
            throw new UserHandleException("Parent category is not active");
        }

        List<Category> childCategories = requests.stream()
                .map(Category::fromRequest)
                .collect(Collectors.toList());
        parentCategory.addChildrenCategories(childCategories);
        Category savedParentCategory = categoryRepository.save(parentCategory);
        return savedParentCategory.getChildrenCategories().stream()
                .map(CategoryResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CategoryResponse updateCategory(Long categoryId, CategoryRequest request) {
        Category category = findByCategoryId(categoryId);
        category.updateFromRequest(request);
        Category savedCategory = categoryRepository.save(category);
        return CategoryResponse.fromEntity(savedCategory);
    }

    @Transactional
    @Override
    public CategoryResponse deleteCategory(Long categoryId) {
        Category category = findByCategoryId(categoryId);
        categoryRepository.delete(category);
        return CategoryResponse.fromEntity(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryByCategoryId(Long categoryId) {
        Category category = findByCategoryId(categoryId);
        return CategoryResponse.fromEntity(category);
    }

    @Override
    public List<CategoryResponse> getChildrenCategoriesByParentId(Long parentId) {
        Category parentCategory = findByCategoryId(parentId);
        List<Category> childrenCategories = parentCategory.getChildrenCategories();
        return childrenCategories.stream()
                .map(CategoryResponse::fromEntity)
                .collect(Collectors.toList());
    }

    private Category findByCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new UserHandleException(NOT_FOUND_CATEGORY_EXCEPTION));
    }
}
