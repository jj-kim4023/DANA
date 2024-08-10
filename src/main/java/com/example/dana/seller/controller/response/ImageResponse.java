package com.example.dana.seller.controller.response;

import com.example.dana.seller.domain.entity.ItemImage;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ImageResponse {
    private Long id; // 이미지 코드
    private String imageName; // 원본 이미지 이름
    private String oriImageName; // 저장된 파일 이름
    private String imagePath; // 이미지 저장 경로

    public static ImageResponse fromEntity(ItemImage itemImage) {
        if (itemImage == null) {
            return null;
        }

        return new ImageResponse(
                itemImage.getId(),
                itemImage.getImageName(),
                itemImage.getOriImageName(),
                itemImage.getImagePath()
        );
    }
}
//    private static ImageRequest imageRequest = new ImageRequest();
//    public static ImageRequest getInstance() {
//        return imageRequest;
//    }

