package com.example.dana.seller.service;

import com.example.dana.seller.controller.request.ImageRequest;
import com.example.dana.seller.controller.response.ImageResponse;

import java.io.IOException;
import java.util.List;

public interface ItemImageService {
    List<ImageResponse> addImages(Long itemId, ImageRequest request) throws IOException;
}
