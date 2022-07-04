package com.itudy.api.domain.study.service;

import com.itudy.api.common.entity.CommonImageVO;
import com.itudy.api.common.service.CommonImageService;
import com.itudy.api.domain.study.domain.*;
import com.itudy.api.domain.study.repository.StudyRepository;
import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyUpdateService {

    @Value("${server.url}")
    private String host;
    private final StudyRepository studyRepository;
    private final UserFindService userFindService;
    private final CommonImageService commonImageService;
    private final PositionFindService positionFindService;

    @Transactional
    public Long save(String name, String description, MultipartFile mf, List<FieldVO> fields, String position){

        UserVO user = userFindService.getMyUserWithAuthorities();
        CommonImageVO image = commonImageService.save(mf);

        StudyVO study = StudyVO.builder()
                .name(name)
                .description(description)
                .imageUrl(host + "/" + image.getName())
                .build();

        for(FieldVO field : fields) study.addField(field);
        study.addMember(positionFindService.findById(position), StudyMemberVO.Role.LEADER.name(), user);

        return studyRepository.save(study).getIdx();
    }



}
