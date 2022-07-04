package com.itudy.api.common.controller;

import com.itudy.api.common.service.CommonImageService;
import com.itudy.api.common.service.CommonPdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommonController {

    private final CommonImageService commonImageService;
    private final CommonPdfService commonPdfService;

    @GetMapping(value = "/common/images/{image-name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getCommonImage(
            @PathVariable(value = "image-name") String imageName
    ) {
        byte[] image = commonImageService.getByName(imageName);

        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @GetMapping(value = "/common/files/{file-name}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getCommonFile(
            @PathVariable(value = "file-name") String fileName
    ) {
        byte[] file = commonPdfService.getByName(fileName);

        return new ResponseEntity<>(file, HttpStatus.OK);
    }
}
