package com.example.dana.seller.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ItemRequest {
    private String itemName;
    private int price;
    private int stockNumber;
    private int count;
    private boolean active;
}
