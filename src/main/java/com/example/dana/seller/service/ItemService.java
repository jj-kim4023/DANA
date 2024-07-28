package com.example.dana.seller.service;

import com.example.dana.seller.controller.request.ItemRequest;
import com.example.dana.seller.controller.response.ItemResponse;

import java.util.List;

public interface ItemService {
    ItemResponse addItem(ItemRequest request);

    ItemResponse updateItem(Long itemId, ItemRequest request);

    ItemResponse deleteItem(Long itemId);

    List<ItemResponse> getAllItems();

    ItemResponse getItemById(Long itemId);
}
