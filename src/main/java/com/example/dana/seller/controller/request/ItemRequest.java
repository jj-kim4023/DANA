package com.example.dana.seller.controller.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ItemRequest {
    private String itemName; // 상품명
    private int price; // 가격
    private int stockNumber; // 재고
    private int count; // 판매 수량
    private boolean active; // 활성 상태
    private String itemDescription; // 상품설명
    private List<MultipartFile> images; // 첨부된 파일들
}

