package com.itudy.api.domain.study.service;

import com.itudy.api.common.entity.CommonImageVO;
import com.itudy.api.common.service.CommonImageService;
import com.itudy.api.domain.study.domain.FieldVO;
import com.itudy.api.domain.study.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldUpdateService {

    private final FieldRepository fieldRepository;
    private final CommonImageService commonImageService;

    @Value("${server.url}")
    private String host;

    public Long save(MultipartFile mf, String name){

        CommonImageVO image = commonImageService.save(mf);

        FieldVO field = FieldVO.builder()
                .imageUrl(host + "/api/v1/common/images/" + image.getName())
                .name(name)
                .build();

        return fieldRepository.save(field).getIdx();
    }

}
