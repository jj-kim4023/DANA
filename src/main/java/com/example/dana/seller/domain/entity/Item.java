package com.example.dana.seller.domain.entity;

import com.example.dana.common.domain.BaseTimeEntity;
import com.example.dana.seller.controller.request.ItemRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품코드
    private String itemName; // 상품명
    private int price; // 가격
    private int stockNumber; // 재고
    private int count; // 판매 수량
    private Long sellerId;

    private String itemDescription; // 상품설명
    private String imagename; // 상품 사진 파일이름
    private String imageUrl; // 상품 사진 파일경로

    private static Item createItem(String name) {
        return new Item();
    }

    public static Item fromRequest(ItemRequest request) {
        return createItem(request.getItemName());
    }

    public void getName(String name) {
        this.itemName = name;
    }

    public void updateFromRequest(ItemRequest request) {
        this.itemName = request.getItemName();
    }
}
