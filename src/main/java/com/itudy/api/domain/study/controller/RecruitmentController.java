package com.itudy.api.domain.study.controller;

import com.itudy.api.domain.common.dto.PageDTO;
import com.itudy.api.domain.common.dto.ResponseWrapper;
import com.itudy.api.domain.study.domain.RecruitmentVO;
import com.itudy.api.domain.study.domain.StudyVO;
import com.itudy.api.domain.study.dto.CreateRecruitmentRequest;
import com.itudy.api.domain.study.dto.FieldDTO;
import com.itudy.api.domain.study.dto.RecruitmentListDTO;
import com.itudy.api.domain.study.service.RecruitmentFindService;
import com.itudy.api.domain.study.service.RecruitmentUpdateService;
import com.itudy.api.domain.study.service.StudyFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RecruitmentController {

    private final RecruitmentUpdateService recruitmentUpdateService;
    private final RecruitmentFindService recruitmentFindService;
    private final StudyFindService studyFindService;


    @PostMapping(path = "/studies/{study-idx}/recruitments")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<String>> saveRecruitment(
            @RequestBody CreateRecruitmentRequest request,
            @PathVariable(value = "study-idx")Long studyIdx
    ) {
        Long saved = recruitmentUpdateService.save(request.getTitle(),
                request.getDescription(),
                studyIdx,
                request.getPortfolioIdx(),
                request.getTechIds(),
                request.getDemands());


        ResponseWrapper<String> data = new ResponseWrapper<>("등록에 성공하였습니다.",
                "recruitment apply success", HttpStatus.CREATED.value());

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping(path = "/studies/recruitments/{recruitment-idx}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<String>> updateRecruitment(
            @RequestBody CreateRecruitmentRequest request,
            @PathVariable(value = "recruitment-idx")Long recruitmentIdx
    ) {
        Long updated = recruitmentUpdateService.update(
                recruitmentIdx,
                request.getTitle(),
                request.getDescription(),
                request.getPortfolioIdx(),
                request.getTechIds(),
                request.getDemands());


        ResponseWrapper<String> data = new ResponseWrapper<>("수정에 성공하였습니다.",
                "recruitment update success", HttpStatus.OK.value());

        return new ResponseEntity<>(data, HttpStatus.OK);
    }


    @GetMapping(path = "/studies/{study-idx}/recruitments")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<PageDTO<RecruitmentListDTO>>> getRecruitments(
            @PathVariable(value = "study-idx")Long studyIdx,
            @PageableDefault(size = 10, direction = Sort.Direction.ASC) Pageable pageable
    ){
        StudyVO study = studyFindService.findByIdx(studyIdx);
        Page<RecruitmentVO> page = recruitmentFindService.getByStudy(study, pageable);
        List<RecruitmentListDTO> dtos = page.stream().map(RecruitmentListDTO::fromEntity).collect(Collectors.toList());

        PageDTO<RecruitmentListDTO> pageDTO = PageDTO.<RecruitmentListDTO>builder()
                .contents(dtos)
                .currentPage(pageable.getPageNumber())
                .totalPage(page.getTotalPages() - 1)
                .build();

        ResponseWrapper<PageDTO<RecruitmentListDTO>> data = new ResponseWrapper<>(
                pageDTO, "recruitment list", HttpStatus.OK.value());

        return new ResponseEntity<>(data, HttpStatus.OK);
    }


}
