package com.example.dana.seller.controller.response;

import com.example.dana.category.controller.response.CategoryResponse;
import com.example.dana.category.domain.entity.Category;
import com.example.dana.seller.domain.entity.Item;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ItemResponse {
    private Long id; // 상품코드
    private static String itemName; // 상품명
    private int price; // 가격
    private int stockNumber; // 재고
    private int count; // 판매 수량
    private List<ItemResponse> items = new ArrayList<>();

    public static ItemResponse fromEntity(Item item) {
        List<ItemResponse> itemResponse = null;
        if (item != null) {
            itemResponse = new ArrayList<>();
        }
        return ItemResponse;
    }
}
