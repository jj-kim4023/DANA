package com.example.dana.seller.controller;

import com.example.dana.common.reponse.ResponseWrapper;
import com.example.dana.seller.controller.request.ImageRequest;
import com.example.dana.seller.controller.request.ItemRequest;
import com.example.dana.seller.controller.response.ImageResponse;
import com.example.dana.seller.controller.response.ItemResponse;
import com.example.dana.seller.service.ItemImageService;
import com.example.dana.seller.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.example.dana.seller.constants.ItemSuccessMessage.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/items")
public class ItemApiController {
    private final ItemService itemService;
    private final ItemImageService itemImageService;

    @PostMapping
    public ResponseWrapper<ItemResponse> addItem(
            @RequestParam("itemName") String itemName,
            @RequestParam("price") int price,
            @RequestParam("stockNumber") int stockNumber,
            @RequestParam("count") int count,
            @RequestParam("active") boolean active,
            @RequestParam("itemDescription") String itemDescription,
            @RequestParam(value = "images", required = false) List<MultipartFile> images) throws IOException {

        ItemRequest request = new ItemRequest(itemName, price, stockNumber, count, active, itemDescription, images);
        itemService.addItem(request);

        ItemResponse itemResponse = itemService.getAllItems().stream()
                .filter(item -> item.getItemName().equals(request.getItemName()))
                .findFirst()
                .orElse(null);

        return ResponseWrapper.SUCCESS(ADD_ITEM_SUCCESS, itemResponse);
    }

//    @PostMapping
//    public ResponseWrapper<ItemResponse> addItem(@RequestBody @Valid ItemRequest request) throws IOException {
//        itemService.addItem(request);
//        ItemResponse itemResponse = itemService.getAllItems().stream()
//                .filter(item -> item.getItemName().equals(request.getItemName()))
//                .findFirst()
//                .orElse(null);
//        return ResponseWrapper.SUCCESS(ADD_ITEM_SUCCESS, itemResponse);
//    }

    @PostMapping("/{itemId}/images")
    public ResponseWrapper<List<ImageResponse>> addItemImages(@PathVariable(name = "itemId") Long itemId,
                                                                  @ModelAttribute ImageRequest request) throws IOException {
        List<ImageResponse> itemImageResponses = itemImageService.addImages(itemId, request);
        return ResponseWrapper.SUCCESS("Images uploaded successfully", itemImageResponses);
    }

    @GetMapping
    public ResponseWrapper<List<ItemResponse>> getAllItems() {
        List<ItemResponse> items = itemService.getAllItems();
        return ResponseWrapper.SUCCESS(null, items);
    }

    @PatchMapping("/{itemId}")
    public ResponseWrapper<ItemResponse> updateItem(@PathVariable(name = "itemId") Long itemId,
                                                    @RequestBody @Valid ItemRequest request) {
        ItemResponse updatedItem = itemService.updateItem(itemId, request);
        return ResponseWrapper.SUCCESS(UPDATE_ITEM_SUCCESS, updatedItem);
    }

    @DeleteMapping("/{itemId}")
    public ResponseWrapper<ItemResponse> deleteItem(@PathVariable(name = "itemId") Long itemId) {
        ItemResponse deletedItem = itemService.deleteItem(itemId);
        return ResponseWrapper.SUCCESS(DELETE_ITEM_SUCCESS, deletedItem);
    }
}
