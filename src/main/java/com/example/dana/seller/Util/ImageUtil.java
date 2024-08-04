package com.example.dana.seller.Util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class ImageUtil {
    public String generateUniqueFileName(String originalFilename) {
        return UUID.randomUUID().toString() + "_" + originalFilename;
    }

    public void saveImage(MultipartFile image, String savePath) throws IOException {
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Image file is empty or null");
        }
        Files.createDirectories(Paths.get(savePath).getParent());
        image.transferTo(new File(savePath));
    }

//    private static final String UPLOAD_DIR = "D:/userimg_img/";
//
//    public static String saveImage(MultipartFile image) throws IOException {
//        if (image.isEmpty()) {
//            throw new IOException("Failed to store empty file");
//        }
//
//        String originalFilename = image.getOriginalFilename();
//        Path uploadPath = Paths.get(UPLOAD_DIR);
//
//        // 디렉토리가 존재하지 않으면 생성
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        String filePath = UPLOAD_DIR + originalFilename;
//        File dest = new File(filePath);
//        image.transferTo(dest);
//
//        return filePath;
//    }
}
