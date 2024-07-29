package com.example.dana.seller.controller.response;

import com.example.dana.seller.domain.entity.Item;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ItemResponse {
    private Long id; // 상품코드
    private String itemName; // 상품명
    private int price; // 가격
    private int stockNumber; // 재고
    private int count; // 판매 수량
    private boolean active; // 활성 상태
    public static ItemResponse fromEntity(Item item) {
        if (item == null) {
            return null;
        }
        return new ItemResponse(
                item.getId(),
                item.getItemName(),
                item.getPrice(),
                item.getStockNumber(),
                item.getCount(),
                item.isActive()
        );
    }
}
