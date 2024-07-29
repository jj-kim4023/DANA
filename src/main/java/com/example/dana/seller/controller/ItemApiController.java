package com.example.dana.seller.controller;

import com.example.dana.common.reponse.ResponseWrapper;
import com.example.dana.seller.controller.request.ItemRequest;
import com.example.dana.seller.controller.response.ItemResponse;
import com.example.dana.seller.domain.entity.Item;
import com.example.dana.seller.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.dana.seller.constants.ItemSuccessMessage.ADD_ITEM_SUCCESS;
import static com.example.dana.seller.controller.response.ItemResponse.fromEntity;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/item")
public class ItemApiController {
    private ItemService itemService;

    @PostMapping
    public ResponseWrapper<ItemResponse> createItem(@RequestBody @Valid ItemRequest request) {
        Item item = new Item(
                null, // ID는 null로 설정하여 데이터베이스에서 자동 생성되도록 합니다.
                request.getItemName(),
                request.getPrice(),
                true // 기본적으로 활성화 상태로 설정합니다.
        );
        Item createItem = itemService.createItem(item);
        return ResponseWrapper.SUCCESS(ADD_ITEM_SUCCESS, ItemResponse.fromEntity(createItem));
    }

    @GetMapping("/{id}")
    public ResponseWrapper<ItemResponse> getItem(@PathVariable Long id) {
        Optional<Item> item = itemService.getItem(id);
        return item.map(i -> ResponseWrapper.ok(fromEntity(i)))
                .orElseGet(() -> ResponseWrapper.notFound().build());
    }

    @GetMapping
    public ResponseWrapper getAllItems() {
        List<Item> items = itemService.getAllItems();
        List<ItemResponse> responses = items.stream()
                .map(ItemResponse::fromEntity)
                .toList();
        return ResponseWrapper.SUCCESS(responses);
    }

    @PutMapping("/{id}")
    public ResponseWrapper<ItemResponse> updateItem(@PathVariable Long id, @RequestBody ItemRequest request) {
        Item item = new Item(
                id,
                request.getItemName(),
                request.getPrice(),
                request.getStockNumber(),
                request.getCount(),
                true
        );
        Item updatedItem = itemService.updateItem(id, item);
        return updatedItem != null ? ResponseEntity.ok(fromEntity(updatedItem))
                : ResponseWrapper.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseWrapper<Void> deleteItem(@PathVariable Long id) {
        boolean deleted = itemService.deleteItem(id);
        return deleted ? ResponseWrapper.noContent().build() : ResponseWrapper.notFound().build();
    }
}
