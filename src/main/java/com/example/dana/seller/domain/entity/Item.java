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

    @Column(nullable = false)
    private String itemName; // 상품명

    @Column(nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private int stockNumber; // 재고

    @Column(nullable = false)
    private int count; // 판매 수량

    @Column(columnDefinition = "boolean default true")
    private boolean active = true; // 활성 상태

    private String itemDescription; // 상품설명
    private String imagename; // 상품 사진 파일이름
    private String imageUrl; // 상품 사진 파일경로


    private Item(String itemName, int price, int stockNumber, int count) {
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.count = count;
    }

    public static Item createItem(String itemName, int price, int stockNumber, int count) {
        return new Item(itemName, price, stockNumber, count);
    }

    public static Item fromRequest(ItemRequest request) {
        return createItem(request.getItemName(), request.getPrice(), request.getStockNumber(), request.getCount());
    }

    public void updateFrom(Item itemDetails) {
        if (itemDetails.getItemName() != null) {
            this.itemName = itemDetails.getItemName();
        }
        if (itemDetails.getPrice() >= 0) {
            this.price = itemDetails.getPrice();
        }
        if (itemDetails.getStockNumber() >= 0) {
            this.stockNumber = itemDetails.getStockNumber();
        }
        if (itemDetails.getCount() >= 0) {
            this.count = itemDetails.getCount();
        }
        this.active = itemDetails.isActive();
    }

    public void updateFromRequest(ItemRequest request) {
        this.itemName = request.getItemName();
        this.price = request.getPrice();
        this.stockNumber = request.getStockNumber();
        this.count = request.getCount();
    }
}
