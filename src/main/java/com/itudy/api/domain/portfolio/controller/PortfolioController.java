package com.itudy.api.domain.portfolio.controller;

import com.itudy.api.common.dto.ResponseWrapper;
import com.itudy.api.domain.portfolio.dto.SavePortfolioRequest;
import com.itudy.api.domain.portfolio.service.PortfolioUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PortfolioController {

    private final PortfolioUpdateService portfolioUpdateService;


    @PostMapping(path = "/users/portfolios", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<String>> savePortfolio(
            @RequestPart(name = "image", required = true) MultipartFile file,
            @RequestPart(name = "data") SavePortfolioRequest request
    ) {

        if (request.getIdx() == null)
            portfolioUpdateService.save(
                    request.getTitle(),
                    request.getDescription(),
                    request.getProjects(),
                    request.getTechIds(),
                    request.getPosition(),
                    file);
        else
            portfolioUpdateService.update(
                    request.getIdx(),
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

}
