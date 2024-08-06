package com.example.dana.seller.controller.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ImageRequest {
    private Long id;
    private String imagePath; // 이미지 경로
    private List<MultipartFile> image;
    private List<String> oriImageName;
    private List<String> stoImageName;
    private int fileAttached; // 파일첨부여부 (첨부1, 미첨부0)
}
