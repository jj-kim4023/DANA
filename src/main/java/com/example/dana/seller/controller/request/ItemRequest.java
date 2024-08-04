package com.example.dana.seller.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
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
//    private String oriImageName; // 원본 사진 이름
//    private String stoImageName; // 서버저장용 파일이름
//    private String imagePath; // 이미지 경로
//    private int fileAttached; // 파일첨부여부 (첨부1, 미첨부0)
    private MultipartFile image;
}
