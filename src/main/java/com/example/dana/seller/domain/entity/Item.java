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

    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    private String imagePath; // 이미지 경로

    private int fileAttached; // 파일 첨부

    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemImage> images;

    private Item(String itemName) {
        this.itemName = itemName;
    }

    public static Item createItem(String itemName) {
        return new Item(itemName);
    }

    public static Item fromRequest(ItemRequest request) {
        Item item = new Item();
        item.itemName = request.getItemName();
//        item.price = request.getPrice();
//        item.stockNumber = request.getStockNumber();
//        item.count = request.getCount(0);
//        item.active = request.isActive(true);
//        item.itemDescription = request.getItemDescription();
//        item.createdTime = LocalDateTime.now();
//        item.modifiedTime = LocalDateTime.now();
//        item.fileAttached = request.getFileAttached();
        return item;
    }

    public void updateFromRequest(ItemRequest request) {
        this.itemName = request.getItemName();
//        this.price = request.getPrice();
//        this.stockNumber = request.getStockNumber();
//        this.count = request.getCount();
//        this.active = request.isActive();
//        this.itemDescription = request.getItemDescription();
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

//    public void updateFrom(Item itemDetails) {
//        if (itemDetails.getItemName() != null) {
//            this.itemName = itemDetails.getItemName();
//        }
//        if (itemDetails.getPrice() >= 0) {
//            this.price = itemDetails.getPrice();
//        }
//        if (itemDetails.getStockNumber() >= 0) {
//            this.stockNumber = itemDetails.getStockNumber();
//        }
//        if (itemDetails.getCount() >= 0) {
//            this.count = itemDetails.getCount();
//        }
//        this.active = itemDetails.isActive();
//    }
}
