package com.itudy.api.domain.portfolio.service;

import com.itudy.api.domain.portfolio.entity.PortfolioVO;
import com.itudy.api.domain.portfolio.repository.PortfolioRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PortfolioFindService {

    private final PortfolioRepository portfolioRepository;

    @Transactional(readOnly = true)
    public PortfolioVO getByIdx(Long idx){
        return portfolioRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }

}
