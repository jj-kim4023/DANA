package com.example.dana.seller.controller.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ImageRequest {
    private List<MultipartFile> images; // 이미지 파일들
}
