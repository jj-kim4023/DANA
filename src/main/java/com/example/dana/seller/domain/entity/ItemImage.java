package com.example.dana.seller.domain.entity;

import com.example.dana.common.domain.BaseTimeEntity;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 이미지 코드

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private String oriImageName; // 원본 파일명
    private String stoImageName; // 저장 파일명
    private String imagePath; // 이미지 경로

    public static ItemImage createItemImage(Item item, String oriImageName, String stoImageName) {
        ItemImage itemImage = new ItemImage();

        itemImage.oriImageName = oriImageName;
        itemImage.stoImageName = stoImageName;
        itemImage.item = item;
        return itemImage;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
