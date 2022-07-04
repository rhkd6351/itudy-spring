package com.itudy.api.domain.portfolio.controller;

import com.itudy.api.common.dto.PageDTO;
import com.itudy.api.common.dto.ResponseWrapper;
import com.itudy.api.domain.portfolio.dto.TechDTO;
import com.itudy.api.domain.portfolio.entity.TechVO;
import com.itudy.api.domain.portfolio.service.TechFindService;
import com.itudy.api.domain.portfolio.service.TechUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
public class TechController {

    private final TechFindService techFindService;
    private final TechUpdateService techUpdateService;


    @GetMapping(path = "/tech")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<PageDTO<TechDTO>>> getTechList(
            @PageableDefault(size = 10, direction = Direction.ASC) Pageable pageable,
            @RequestParam(defaultValue = "") String query
    ) {
        Page<TechVO> page = techFindService.getAllByQuery(pageable, query);
        List<TechDTO> dtos = page.stream().map(TechDTO::fromEntity).collect(Collectors.toList());

        PageDTO<TechDTO> pageDTO = PageDTO.<TechDTO>builder()
                .contents(dtos)
                .currentPage(pageable.getPageNumber())
                .totalPage(page.getTotalPages() - 1)
                .build();

        ResponseWrapper<PageDTO<TechDTO>> data = new ResponseWrapper<>(
                pageDTO, "tech list", HttpStatus.OK.value());

        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    @PostMapping(path = "/admin/tech", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<String>> saveTech(
            @RequestPart(name = "image", required = true) MultipartFile mf,
            @RequestPart(name = "name", required = true) String name
    ) {
        Long save = techUpdateService.save(mf, name);

        ResponseWrapper<String> data = new ResponseWrapper<>("등록에 성공하였습니다.",
                "tech apply success", HttpStatus.CREATED.value());

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/tech/{tech-idx}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<String>> deleteTech(
            @PathVariable(name = "tech-idx") Long idx
    ) {
        techUpdateService.delete(idx);

        ResponseWrapper<String> data = new ResponseWrapper<>("삭제에 성공하였습니다.",
                "tech delete success", HttpStatus.NO_CONTENT.value());

        return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
    }

}
