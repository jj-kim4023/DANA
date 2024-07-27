package com.example.dana.seller.controller;

import com.example.dana.seller.domain.entity.Item;
import com.example.dana.seller.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemApiController {
    private final ItemService itemService;

    @GetMapping
    public List<Item> getItems() {

    }
}
