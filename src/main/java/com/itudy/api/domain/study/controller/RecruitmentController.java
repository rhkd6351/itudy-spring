package com.itudy.api.domain.study.controller;

import com.itudy.api.domain.common.dto.ResponseWrapper;
import com.itudy.api.domain.study.dto.CreateRecruitmentRequest;
import com.itudy.api.domain.study.service.RecruitmentUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RecruitmentController {

    private final RecruitmentUpdateService recruitmentUpdateService;


    @PostMapping(path = "/studies/{study-idx}/recruitments")
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


}
