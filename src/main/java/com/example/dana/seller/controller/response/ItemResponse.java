package com.example.dana.seller.controller.response;

import com.example.dana.seller.domain.entity.Item;
import com.example.dana.seller.domain.entity.ItemImage;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<String> imageUrls; // 이미지 URL 리스트
    private LocalDateTime createdTime; // 작성 시간
    private LocalDateTime modifiedTime; // 수정 시간

    public static ItemResponse fromEntity(Item item) {
        List<String> imageUrls = item.getImages().stream()
                .map(ItemImage::getStoImageName) // 실제 URL 생성 로직 추가 필요
                .collect(Collectors.toList());

        return new ItemResponse(
                item.getId(),
                item.getItemName(),
                item.getPrice(),
                item.getStockNumber(),
                item.getCount(),
                item.isActive(),
                imageUrls,
                item.getCreatedTime(),
                item.getModifiedTime()
        );
    }

    public static List<ItemResponse> fromEntities(List<Item> items) {
        if (items == null) {
            return List.of();
        }
        return items.stream()
                .map(ItemResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
