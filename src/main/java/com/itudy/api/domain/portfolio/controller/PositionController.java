package com.itudy.api.domain.portfolio.controller;

import com.itudy.api.domain.common.dto.ResponseWrapper;
import com.itudy.api.domain.study.domain.PositionVO;
import com.itudy.api.domain.study.service.PositionFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class PositionController {

    private final PositionFindService positionFindService;

    @GetMapping("/positions")
    public ResponseEntity<ResponseWrapper<List<String>>> getPositions(){
        List<PositionVO> positions = positionFindService.findAll();

        List<String> result = positions.stream().map(PositionVO::getName).toList();

        ResponseWrapper<List<String>> data = new ResponseWrapper<>(result,
                "position list", HttpStatus.OK.value());

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
