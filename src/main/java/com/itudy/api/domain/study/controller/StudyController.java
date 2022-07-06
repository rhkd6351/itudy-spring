package com.itudy.api.domain.study.controller;

import com.itudy.api.domain.common.dto.PageDTO;
import com.itudy.api.domain.common.dto.ResponseWrapper;
import com.itudy.api.domain.study.domain.FieldVO;
import com.itudy.api.domain.study.domain.StudyVO;
import com.itudy.api.domain.study.dto.StudyCreateRequest;
import com.itudy.api.domain.study.dto.StudyDTO;
import com.itudy.api.domain.study.service.FieldFindService;
import com.itudy.api.domain.study.service.StudyFindService;
import com.itudy.api.domain.study.service.StudyUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StudyController {

    private final StudyUpdateService studyUpdateService;
    private final FieldFindService fieldFindService;
    private final StudyFindService studyFindService;

    @PostMapping(path = "/studies", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ResponseWrapper<String>> saveStudy(
            @RequestPart(name = "image", required = true) MultipartFile mf,
            @RequestPart(name = "data", required = true) @Validated StudyCreateRequest request
    ) {
        List<FieldVO> fieldList = request.getFields().stream().map(fieldFindService::findByIdx).toList();
        studyUpdateService.save(request.getName(), request.getDescription(), mf, fieldList, request.getPosition());

        ResponseWrapper<String> data = new ResponseWrapper<>("등록에 성공하였습니다.",
                "study apply success", HttpStatus.CREATED.value());

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping(path = "/studies")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ResponseWrapper<PageDTO<StudyDTO>>> getMyStudy(
            @PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<StudyVO> page = studyFindService.getJoinedStudies(pageable);
        List<StudyDTO> dtos = page.stream().map(StudyDTO::fromEntityWithMembers).collect(Collectors.toList());

        PageDTO<StudyDTO> pageDTO = PageDTO.<StudyDTO>builder()
                .contents(dtos)
                .currentPage(pageable.getPageNumber())
                .totalPage(page.getTotalPages() - 1)
                .build();

        ResponseWrapper<PageDTO<StudyDTO>> data = new ResponseWrapper<>(
                pageDTO, "joined study list", HttpStatus.OK.value());

        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    @GetMapping(path = "/admin/studies")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<PageDTO<StudyDTO>>> getAllStudy(
            @PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(defaultValue = "") String query
    ) {
        Page<StudyVO> page = studyFindService.getAllByQuery(pageable, query);
        List<StudyDTO> dtos = page.stream().map(StudyDTO::fromEntityWithMembers).collect(Collectors.toList());

        PageDTO<StudyDTO> pageDTO = PageDTO.<StudyDTO>builder()
                .contents(dtos)
                .currentPage(pageable.getPageNumber())
                .totalPage(page.getTotalPages() - 1)
                .build();

        ResponseWrapper<PageDTO<StudyDTO>> data = new ResponseWrapper<>(
                pageDTO, "study list", HttpStatus.OK.value());

        return new ResponseEntity<>(data, HttpStatus.OK);

    }


}
