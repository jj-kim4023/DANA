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

import static com.example.dana.seller.constants.ItemSuccessMessage.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/items")
public class ItemApiController {
    private final ItemService itemService;

    @PostMapping
    public ResponseWrapper<ItemResponse> addItem(@RequestBody @Valid ItemRequest request) {
        return ResponseWrapper.SUCCESS(ADD_ITEM_SUCCESS, itemService.addItem(request));
    }

    @GetMapping
    public ResponseWrapper<List<ItemResponse>> getAllItems() {
        return ResponseWrapper.SUCCESS(null, itemService.getAllItems());
    }

    @PatchMapping("/{itemId}")
    public ResponseWrapper<ItemResponse> updateItem(@PathVariable(name = "itemId") Long itemId, @RequestBody @Valid ItemRequest request) {
        return ResponseWrapper.SUCCESS(UPDATE_ITEM_SUCCESS, itemService.updateItem(itemId, request));
    }

    @DeleteMapping("/{itemId}")
    public ResponseWrapper<ItemResponse> deleteItem(@PathVariable(name = "itemId") Long itemId) {
        return ResponseWrapper.SUCCESS(DELETE_ITEM_SUCCESS, itemService.deleteItem(itemId));
    }
}
