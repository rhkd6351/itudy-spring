package com.itudy.api.domain.portfolio.repository;

import com.itudy.api.domain.portfolio.entity.PortfolioVO;
import com.itudy.api.domain.user.domain.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioVO, Long> {

    @Query(value = "select m from PortfolioVO m where m.user = :user")
    @EntityGraph(attributePaths = {"position", "user", "techs", "projects", "projects.techs"})
    Page<PortfolioVO> findByUser(UserVO user, Pageable pageable);
}
