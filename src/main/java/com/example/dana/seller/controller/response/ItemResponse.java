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












//    public static ItemResponse fromEntity(Item item) {
//        List<String> imageUrls = item.getImages().stream()
//                .map(ItemImage::getStoImageName) // 실제 URL 생성 로직 추가 필요
//                .collect(Collectors.toList());
//
//        return new ItemResponse(
//                item.getId(),
//                item.getItemName(),
//                item.getPrice(),
//                item.getStockNumber(),
//                item.getCount(),
//                item.isActive()
//        );
//    }
//
//    public static List<ItemResponse> fromEntities(List<Item> items) {
//        if (items == null) {
//            return List.of();
//        }
//        return items.stream()
//                .map(ItemResponse::fromEntity)
//                .collect(Collectors.toList());
//    }
//}
