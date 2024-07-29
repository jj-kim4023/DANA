package com.example.dana.seller.service;

import com.example.dana.seller.controller.request.ItemRequest;
import com.example.dana.seller.controller.response.ItemResponse;
import com.example.dana.seller.domain.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item createItem(Item item);
    Optional<Item> getItem(Long id);
    List<Item> getAllItems();
    Item updateItem(Long id, Item itemDetails);
    boolean deleteItem(Long id);
}
