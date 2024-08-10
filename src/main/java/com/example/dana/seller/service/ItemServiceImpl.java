package com.example.dana.seller.service;

import com.example.dana.seller.controller.request.ItemRequest;
import com.example.dana.seller.controller.response.ImageResponse;
import com.example.dana.seller.controller.response.ItemResponse;
import com.example.dana.seller.domain.entity.Item;
import com.example.dana.seller.domain.entity.ItemImage;
import com.example.dana.seller.domain.repository.ItemImageRepository;
import com.example.dana.seller.domain.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;

    @Override
    public void addItem(ItemRequest request) throws IOException {
        Item item = Item.fromRequest(request);
        itemRepository.save(item);

        // 여기에 이미지 처리 로직 추가 가능
    }

    @Override
    public List<ItemResponse> getAllItems() {
        return itemRepository.findAll().stream()
                .map(item -> {
                    List<ItemImage> itemImages = itemImageRepository.findByItemId(item.getId());
                    List<ImageResponse> imageResponses = itemImages.stream()
                            .map(ImageResponse::fromEntity)
                            .collect(Collectors.toList());
                    return ItemResponse.fromEntity(item, imageResponses);
                })
                .collect(Collectors.toList());
    }

    @Override
    public ItemResponse updateItem(Long itemId, ItemRequest request) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item을 찾을 수 없습니다"));
        item.updateFromRequest(request);
        itemRepository.save(item);

        List<ItemImage> itemImages = itemImageRepository.findByItemId(itemId);
        List<ImageResponse> imageResponses = itemImages.stream()
                .map(ImageResponse::fromEntity)
                .collect(Collectors.toList());

        return ItemResponse.fromEntity(item, imageResponses);
    }

    @Override
    public ItemResponse deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item을 찾을 수 없습니다"));
        itemRepository.delete(item);

        // 여기에 이미지 삭제 로직 추가 가능
        List<ItemImage> itemImages = itemImageRepository.findByItemId(itemId);
        List<ImageResponse> imageResponses = itemImages.stream()
                .map(ImageResponse::fromEntity)
                .collect(Collectors.toList());

        return ItemResponse.fromEntity(item, imageResponses);
    }
}
