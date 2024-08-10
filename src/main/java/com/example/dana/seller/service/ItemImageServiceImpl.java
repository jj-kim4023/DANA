package com.example.dana.seller.service;

import com.example.dana.seller.controller.request.ImageRequest;
import com.example.dana.seller.controller.response.ImageResponse;
import com.example.dana.seller.domain.entity.Item;
import com.example.dana.seller.domain.entity.ItemImage;
import com.example.dana.seller.domain.repository.ItemImageRepository;
import com.example.dana.seller.domain.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImageServiceImpl implements ItemImageService {
    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;

    @Override
    public List<ImageResponse> addImages(Long itemId, ImageRequest request) throws IOException {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item을 찾을 수 없습니다"));

        return request.getImages().stream()
                .map(file -> {
                    try {
                        String originalFilename = file.getOriginalFilename();
                        if (originalFilename == null) {
                            return null;
                        }

                        String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                        String savePath = "D:/userimg_img/" + storedFileName;

                        file.transferTo(new File(savePath));

                        ItemImage itemImage = ItemImage.createItemImage(
                                originalFilename,
                                storedFileName,
                                savePath
                        );
                        itemImage.setItem(item);
                        itemImageRepository.save(itemImage);

                        return ImageResponse.fromEntity(itemImage);
                    } catch (IOException e) {
                        throw new RuntimeException("파일 저장 중 오류가 발생했습니다", e);
                    }
                })
                .collect(Collectors.toList());
    }
}
