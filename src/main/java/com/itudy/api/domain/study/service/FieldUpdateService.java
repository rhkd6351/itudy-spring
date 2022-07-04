package com.itudy.api.domain.study.service;

import com.itudy.api.common.entity.CommonImageVO;
import com.itudy.api.common.service.CommonImageService;
import com.itudy.api.domain.study.domain.FieldVO;
import com.itudy.api.domain.study.repository.FieldRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
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
    private final FieldFindService fieldFindService;

    @Value("${server.url}")
    private String host;

    @Transactional
    public Long save(MultipartFile mf, String name) {

        if (fieldRepository.existsByName(name))
            throw new ApiException(ExceptionEnum.DUPLICATED_EXCEPTION);

        CommonImageVO image = commonImageService.save(mf);

        FieldVO field = FieldVO.builder()
                .imageUrl(host + "/" + image.getName())
                .name(name)
                .build();

        return fieldRepository.save(field).getIdx();
    }

    @Transactional
    public void delete(Long idx) {
        FieldVO field = fieldFindService.findByIdx(idx);
        fieldRepository.delete(field);
    }
}
