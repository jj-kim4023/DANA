package com.example.dana.seller.domain.entity;

import com.example.dana.common.domain.BaseTimeEntity;
import com.example.dana.seller.controller.request.ImageRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemImage extends BaseTimeEntity {
    @Id
    @Column(name = "item_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 이미지 코드

    private String imageName; // 원본 이미지 이름
    private String oriImageName; // 저장된 파일 이름
    private String imagePath; // 이미지 저장 경로

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // ItemImage 엔티티를 생성하는 정적 메서드
    public static ItemImage createItemImage(String imageName, String oriImageName, String imagePath) {
        ItemImage itemImage = new ItemImage();
        itemImage.imageName = imageName;
        itemImage.oriImageName = oriImageName;
        itemImage.imagePath = imagePath;
        return itemImage;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

//    public static ItemImage createItemImage(Item item, String imageName, String oriImageName) {
//        ItemImage itemImage = new ItemImage();
//
//        itemImage.oriImageName = imageName;
//        itemImage.stoImageName = oriImageName;
//        itemImage.item = item;
//        return itemImage;
//    }
//
//    public void setImagePath(String imagePath) {
//        this.imagePath = imagePath;
//    }
//}
