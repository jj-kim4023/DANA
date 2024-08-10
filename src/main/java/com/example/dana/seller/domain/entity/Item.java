package com.example.dana.seller.domain.entity;

import com.example.dana.common.domain.BaseTimeEntity;
import com.example.dana.seller.controller.request.ItemRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemImage> itemImages; // 연관된 이미지들

    // 필요한 경우 추가 생성자 정의
    public Item(String itemName, int price, int stockNumber, int count, boolean active, String itemDescription) {
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.count = count;
        this.active = active;
        this.itemDescription = itemDescription;
    }

    // DTO에서 엔티티 생성
    public static Item fromRequest(ItemRequest request) {
        return new Item(
                request.getItemName(),
                request.getPrice(),
                request.getStockNumber(),
                request.getCount(),
                request.isActive(),
                request.getItemDescription()
        );
    }

    public void updateFromRequest(ItemRequest request) {
        this.itemName = request.getItemName();
        this.price = request.getPrice();
        this.stockNumber = request.getStockNumber();
        this.count = request.getCount();
        this.active = request.isActive();
        this.itemDescription = request.getItemDescription();
    }
}
