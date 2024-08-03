package com.example.dana.seller.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ItemRequest {
    @NotBlank(message = "상품명은 필수 입력 항목입니다.")
    private String itemName;
//    private int price;
//    private int stockNumber;
//    private int count;
//    private boolean active;
//    private String itemDescription;
    private MultipartFile image;
}
