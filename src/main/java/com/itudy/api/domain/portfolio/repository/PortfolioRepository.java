package com.itudy.api.domain.portfolio.repository;

import com.itudy.api.domain.portfolio.entity.PortfolioVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioVO, Long> {

}
