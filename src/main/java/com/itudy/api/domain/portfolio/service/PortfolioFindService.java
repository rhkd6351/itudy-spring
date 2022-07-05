package com.itudy.api.domain.portfolio.service;

import com.itudy.api.domain.portfolio.entity.PortfolioVO;
import com.itudy.api.domain.portfolio.repository.PortfolioRepository;
import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.service.UserFindService;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PortfolioFindService {

    private final PortfolioRepository portfolioRepository;
    private final UserFindService userFindService;

    @Transactional(readOnly = true)
    public PortfolioVO getByIdx(Long idx) {
        return portfolioRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }

    @Transactional(readOnly = true)
    public Page<PortfolioVO> getMyPortfolios(Pageable pageable){
        UserVO user = userFindService.getMyUserWithAuthorities();
        return portfolioRepository.findByUser(user, pageable);
    }


}
