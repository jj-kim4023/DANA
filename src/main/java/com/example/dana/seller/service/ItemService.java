package com.example.dana.seller.service;

import com.example.dana.category.controller.request.CategoryRequest;
import com.example.dana.category.controller.response.CategoryResponse;
import com.example.dana.seller.controller.request.ItemRequest;
import com.example.dana.seller.controller.response.ItemResponse;
import com.example.dana.seller.domain.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    ItemResponse addItem(ItemRequest request);

    ItemResponse updateItem(Long ItemId, ItemRequest request);

    ItemResponse deleteItem(Long itemId);

    List<ItemResponse> getAllItems();

    ItemResponse getItemByItemId(Long ItemId);
}
