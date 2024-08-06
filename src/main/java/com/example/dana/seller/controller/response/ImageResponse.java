package com.example.dana.seller.controller.response;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ImageResponse {
    private Long id;
    private String imagePath; // 이미지 경로
    private List<MultipartFile> image;
    private List<String> oriImageName;
    private List<String> stoImageName;

    public static ImageResponse fromMultipartFile(MultipartFile image) {
        return new ImageResponse();
    }
}
