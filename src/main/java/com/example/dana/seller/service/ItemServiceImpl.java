package com.example.dana.seller.service;

import com.example.dana.common.exception.UserHandleException;
import com.example.dana.seller.Util.ImageUtil;
import com.example.dana.seller.controller.request.ItemRequest;
import com.example.dana.seller.controller.response.ItemResponse;
import com.example.dana.seller.domain.entity.Item;
import com.example.dana.seller.domain.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.dana.category.constants.CategoryErrorMessage.NOT_FOUND_CATEGORY_EXCEPTION;
import static com.example.dana.seller.constants.ItemSuccessMessage.ADD_ITEM_SUCCESS;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private ImageUtil imageUtil;

    @Transactional
    @Override
    public ItemResponse addItem(ItemRequest request) {
        MultipartFile image = request.getImage();
        String imagePath = null;

        if (image != null && !image.isEmpty()) {
            try {
                imagePath = ImageUtil.saveImage(image);
            } catch (IOException e) {
                throw new UserHandleException(ADD_ITEM_SUCCESS);
            }
        }
        Item item = Item.fromRequest(request);
        item.setImagePath(imagePath); // 이미지 경로 설정
        Item savedItem = itemRepository.save(item);

        return ItemResponse.fromEntity(savedItem);
    }

    @Transactional
    @Override
    public ItemResponse updateItem(Long itemId, ItemRequest request) {
        Item item = findByItemId(itemId);
        item.updateFromRequest(request);
        Item savedItem = itemRepository.save(item);
        return ItemResponse.fromEntity(savedItem);
    }

    @Transactional
    @Override
    public ItemResponse deleteItem(Long itemId) {
        Item item = findByItemId(itemId);
        itemRepository.delete(item);
        return ItemResponse.fromEntity(item);
    }

    @Override
    public List<ItemResponse> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(ItemResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ItemResponse getItemByItemId(Long itemId) {
        Item item = findByItemId(itemId);
        return ItemResponse.fromEntity(item);
    }

    private Item findByItemId(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new UserHandleException(NOT_FOUND_CATEGORY_EXCEPTION));
    }
}
