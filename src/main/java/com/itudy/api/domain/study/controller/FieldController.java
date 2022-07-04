package com.itudy.api.domain.study.controller;

import com.itudy.api.common.dto.ResponseWrapper;
import com.itudy.api.domain.study.domain.FieldVO;
import com.itudy.api.domain.study.dto.FieldDTO;
import com.itudy.api.domain.study.service.FieldFindService;
import com.itudy.api.domain.study.service.FieldUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FieldController {

    private final FieldFindService fieldFindService;
    private final FieldUpdateService fieldUpdateService;


    @GetMapping(path = "/studies/fields")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<List<FieldDTO>>> getFields(
            @PageableDefault(size = 10, direction = Direction.ASC) Pageable pageable,
            @RequestParam(defaultValue = "") String query
    ) {
        List<FieldVO> fields = fieldFindService.getAllByQuery(pageable, query);

        ResponseWrapper<List<FieldDTO>> data = new ResponseWrapper<>(fields.stream().map(FieldDTO::fromEntity).collect(Collectors.toList()),
                "field list", HttpStatus.OK.value());

        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    @PostMapping(path = "/studies/fields", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<String>> saveField(
            @RequestPart(name = "image", required = true) MultipartFile mf,
            @RequestParam(name = "name", required = true) String name
    ) {
        Long save = fieldUpdateService.save(mf, name);

        ResponseWrapper<String> data = new ResponseWrapper<>("등록에 성공하였습니다.",
                "field list", HttpStatus.CREATED.value());

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

}
