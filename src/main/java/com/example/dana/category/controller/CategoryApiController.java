package com.example.dana.category.controller;

import com.example.dana.category.controller.request.CategoryRequest;
import com.example.dana.category.controller.response.CategoryResponse;
import com.example.dana.category.domain.entity.Category;
import com.example.dana.category.service.CategoryService;
import com.example.dana.common.reponse.ResponseWrapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.dana.category.constants.CategorySuccessMessage.ADD_CATEGORY_SUCCESS;
import static com.example.dana.category.constants.CategorySuccessMessage.UPDATE_CATEGORY_SUCCESS;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/categories")
public class CategoryApiController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseWrapper<CategoryResponse> addCategory(@RequestBody @Valid CategoryRequest request) {
        return ResponseWrapper.SUCCESS(ADD_CATEGORY_SUCCESS, categoryService.addParentCategory(request));
    }
    /**여기*/
    @PostMapping("/parentId")
    public ResponseWrapper<CategoryResponse> addChildCategory(@PathVariable(name = "parentId") Long parentId, @RequestBody List<CategoryRequest> requests) {
        return ResponseWrapper.SUCCESS(ADD_CATEGORY_SUCCESS, categoryService.addChildrenCategories(parentId, requests));
    }

    @GetMapping("/categoryId")
    public ResponseWrapper<List<CategoryResponse>> getAllCategories(@PathVariable(name = "categoryId")Long categoryId) {
        return ResponseWrapper.SUCCESS(null, categoryService.getAllCategories());
    }

    @PatchMapping("/update")
    public ResponseWrapper<CategoryResponse> updateCategory(@PathVariable(name = "categoryId") Long categoryId, @RequestBody @Valid CategoryRequest request) {
        return ResponseWrapper.SUCCESS(UPDATE_CATEGORY_SUCCESS, categoryService.updateCategory(categoryId, request));
    }
}
