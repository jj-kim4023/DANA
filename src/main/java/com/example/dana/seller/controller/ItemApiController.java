package com.example.dana.seller.controller;

import com.example.dana.common.reponse.ResponseWrapper;
import com.example.dana.seller.controller.request.ItemRequest;
import com.example.dana.seller.controller.response.ItemResponse;
import com.example.dana.seller.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.dana.seller.constants.ItemSuccessMessage.ADD_ITEM_SUCCESS;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/item")
public class ItemApiController {
    private final ItemService itemService;

    @GetMapping("/")
    public ResponseWrapper<List<ItemResponse>> findAll() {
        return ResponseWrapper.SUCCESS(null, itemService.getAllItems());
    }

    @PostMapping
    public ResponseWrapper<ItemResponse> addItem(@RequestBody @Valid ItemRequest request) {
        return ResponseWrapper.SUCCESS(ADD_ITEM_SUCCESS, itemService.addItem(request));
    }
}
