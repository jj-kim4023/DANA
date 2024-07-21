package com.example.dana.category.controller;

import com.example.dana.category.controller.request.CategoryRequest;
import com.example.dana.category.controller.response.CategoryResponse;
import com.example.dana.category.service.CategoryService;
import com.example.dana.common.reponse.ResponseWrapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.dana.category.constants.CategorySuccessMessage.ADD_CATEGORY_SUCCESS;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/categories")
public class CategoryApiController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseWrapper<CategoryResponse> addParentCategory(@RequestBody @Valid CategoryRequest request) {
        return ResponseWrapper.SUCCESS(ADD_CATEGORY_SUCCESS, categoryService.addParentCategory(request));
    }
}
