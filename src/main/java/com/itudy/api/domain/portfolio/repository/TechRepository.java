package com.itudy.api.domain.portfolio.repository;

import com.itudy.api.domain.portfolio.entity.TechVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechRepository extends JpaRepository<TechVO, Long> {

    Page<TechVO> findByNameContains(Pageable pageable, String name);

    boolean existsByName(String name);
}
