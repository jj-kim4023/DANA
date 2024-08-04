package com.example.dana.seller.service;

import com.example.dana.common.exception.UserHandleException;
import com.example.dana.seller.Util.ImageUtil;
import com.example.dana.seller.controller.request.ItemRequest;
import com.example.dana.seller.controller.response.ItemResponse;
import com.example.dana.seller.domain.entity.Item;
import com.example.dana.seller.domain.entity.ItemImage;
import com.example.dana.seller.domain.repository.ItemImageRepository;
import com.example.dana.seller.domain.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.dana.category.constants.CategoryErrorMessage.NOT_FOUND_CATEGORY_EXCEPTION;
import static com.example.dana.seller.constants.ItemErrorMessage.ADD_ITEM_FAIL;
import static com.example.dana.seller.constants.ItemSuccessMessage.ADD_ITEM_SUCCESS;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;
    private final ImageUtil imageUtil;

    @Value("${image.upload.dir}")
    private String uploadDir;

    private String generateUniqueFileName(String originalFilename) {
        return System.currentTimeMillis() + "_" + originalFilename;
    }

    private void saveFile(MultipartFile file, String savePath) throws IOException {
        file.transferTo(new File(savePath));
    }

    @Override
    public ItemResponse addItem(ItemRequest request) {
        MultipartFile image = request.getImage();
        Item item = Item.fromRequest(request);
        itemRepository.save(item);

        if (image != null && !image.isEmpty()) {
            try {
                String originalFilename = image.getOriginalFilename();
                String storedFileName = generateUniqueFileName(originalFilename); // 유니크 파일 이름 생성
                String savePath = uploadDir + storedFileName; // 파일 저장 경로

                // 파일 저장
                saveFile(image, savePath);

                // ItemImage 엔티티 생성 및 저장
                ItemImage itemImage = ItemImage.createItemImage(item, originalFilename, storedFileName);
                itemImage.setImagePath(savePath); // 이미지 경로 설정
                itemImageRepository.save(itemImage); // ItemImage 저장
            } catch (IOException e) {
                throw new UserHandleException(ADD_ITEM_FAIL); // 예외 처리
            }
        }

        return ItemResponse.fromEntity(item); // 응답 반환
    }

    @Override
    public ItemResponse updateItem(Long itemId, ItemRequest request) {
        Item item = findByItemId(itemId);
        item.updateFromRequest(request);
        Item savedItem = itemRepository.save(item);
        return ItemResponse.fromEntity(savedItem);
    }

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

//    @Transactional
//    @Override
//    public ItemResponse addItem(ItemRequest request) {
//        MultipartFile image = request.getImage();
//        String imagePath = null;
//
//        if (image != null && !image.isEmpty()) {
//            try {
//                imagePath = ImageUtil.saveImage(image);
//            } catch (IOException e) {
//                throw new UserHandleException(ADD_ITEM_SUCCESS);
//            }
//        }
//        Item item = Item.fromRequest(request);
//        item.setImagePath(imagePath); // 이미지 경로 설정
//        Item savedItem = itemRepository.save(item);
//
//        return ItemResponse.fromEntity(savedItem);
//    }
//
//    @Transactional
//    @Override
//    public ItemResponse updateItem(Long itemId, ItemRequest request) {
//        Item item = findByItemId(itemId);
//        item.updateFromRequest(request);
//        Item savedItem = itemRepository.save(item);
//        return ItemResponse.fromEntity(savedItem);
//    }
//
//    @Transactional
//    @Override
//    public ItemResponse deleteItem(Long itemId) {
//        Item item = findByItemId(itemId);
//        itemRepository.delete(item);
//        return ItemResponse.fromEntity(item);
//    }
//
//    @Override
//    public List<ItemResponse> getAllItems() {
//        List<Item> items = itemRepository.findAll();
//        return items.stream()
//                .map(ItemResponse::fromEntity)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public ItemResponse getItemByItemId(Long itemId) {
//        Item item = findByItemId(itemId);
//        return ItemResponse.fromEntity(item);
//    }
//
//    private Item findByItemId(Long itemId) {
//        return itemRepository.findById(itemId)
//                .orElseThrow(() -> new UserHandleException(NOT_FOUND_CATEGORY_EXCEPTION));
//    }
