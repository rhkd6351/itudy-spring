package com.itudy.api.domain.portfolio.service;

import com.itudy.api.common.entity.CommonImageVO;
import com.itudy.api.common.service.CommonImageService;
import com.itudy.api.domain.portfolio.entity.TechVO;
import com.itudy.api.domain.portfolio.repository.TechRepository;
import com.itudy.api.domain.study.domain.FieldVO;
import com.itudy.api.domain.study.repository.FieldRepository;
import com.itudy.api.domain.study.service.FieldFindService;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class TechUpdateService {

    private final TechRepository techRepository;
    private final CommonImageService commonImageService;
    private final TechFindService techFindService;

    @Value("${server.url}")
    private String host;

    @Transactional
    public Long save(MultipartFile mf, String name) {

        if (techRepository.existsByName(name))
            throw new ApiException(ExceptionEnum.DUPLICATED_EXCEPTION);

        CommonImageVO image = commonImageService.save(mf);

        TechVO tech = TechVO.builder()
                .imageUrl(host + "/api/v1/common/images/" + image.getName())
                .name(name)
                .build();

        return techRepository.save(tech).getIdx();
    }

    @Transactional
    public void delete(Long idx) {
        TechVO tech = techFindService.findByIdx(idx);
        techRepository.delete(tech);
    }
}
