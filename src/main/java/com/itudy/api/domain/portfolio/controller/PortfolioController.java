package com.itudy.api.domain.portfolio.controller;

import com.itudy.api.domain.common.dto.PageDTO;
import com.itudy.api.domain.common.dto.ResponseWrapper;
import com.itudy.api.domain.portfolio.dto.PortfolioDTO;
import com.itudy.api.domain.portfolio.dto.SavePortfolioRequest;
import com.itudy.api.domain.portfolio.entity.PortfolioVO;
import com.itudy.api.domain.portfolio.service.PortfolioFindService;
import com.itudy.api.domain.portfolio.service.PortfolioUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PortfolioController {

    private final PortfolioUpdateService portfolioUpdateService;
    private final PortfolioFindService portfolioFindService;


    @PostMapping(path = "/users/portfolios", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<String>> savePortfolio(
            @RequestPart(name = "file", required = true) MultipartFile file,
            @RequestPart(name = "data") SavePortfolioRequest request
    ) {

        Long saved = portfolioUpdateService.save(
                request.getTitle(),
                request.getDescription(),
                request.getProjects(),
                request.getTechIds(),
                request.getPosition(),
                file);


        ResponseWrapper<String> data = new ResponseWrapper<>("등록에 성공하였습니다.",
                "portfolio apply success", HttpStatus.CREATED.value());

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }


    @PutMapping(path = "/users/portfolios/{portfolio-idx}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<String>> updatePortfolio(
            @RequestPart(name = "file", required = true) MultipartFile file,
            @RequestPart(name = "data") SavePortfolioRequest request,
            @PathVariable(value = "portfolio-idx") Long portfolioIdx
    ) {

        Long saved = portfolioUpdateService.update(
                portfolioIdx,
                request.getTitle(),
                request.getDescription(),
                request.getProjects(),
                request.getTechIds(),
                request.getPosition(),
                file);


        ResponseWrapper<String> data = new ResponseWrapper<>("수정에 성공하였습니다.",
                "portfolio update success", HttpStatus.OK.value());

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(path = "/users/portfolios")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<PageDTO<PortfolioDTO>>> getMyPortfolios(
            @PageableDefault(size = 10, direction = Sort.Direction.ASC) Pageable pageable
    ) {

        Page<PortfolioVO> page = portfolioFindService.findMyPortfolios(pageable);
        List<PortfolioDTO> dtos = page.stream().map(PortfolioDTO::fromEntity).toList();

        PageDTO<PortfolioDTO> pageDTO = PageDTO.<PortfolioDTO>builder()
                .contents(dtos)
                .currentPage(pageable.getPageNumber())
                .totalPage(page.getTotalPages() - 1)
                .build();

        ResponseWrapper<PageDTO<PortfolioDTO>> data = new ResponseWrapper<>(pageDTO,
                "my portfolio list", HttpStatus.OK.value());

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
