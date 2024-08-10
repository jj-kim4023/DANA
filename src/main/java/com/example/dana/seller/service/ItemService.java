package com.example.dana.seller.service;

import com.example.dana.seller.controller.request.ImageRequest;
import com.example.dana.seller.controller.request.ItemRequest;
import com.example.dana.seller.controller.response.ItemResponse;

import java.io.IOException;
import java.util.List;

public interface ItemService {

    void addItem(ItemRequest request) throws IOException;
    List<ItemResponse> getAllItems();
    ItemResponse updateItem(Long itemId, ItemRequest request);
    ItemResponse deleteItem(Long itemId);
//
//    void addItem(ImageRequest request) throws IOException;
//
//    ItemResponse updateItem(Long ItemId, ItemRequest request);
//
//    ItemResponse deleteItem(Long ItemId);
//
//    List<ItemResponse> getAllItems();
//
//    ItemResponse getItemByItemId(Long ItemId);
}
