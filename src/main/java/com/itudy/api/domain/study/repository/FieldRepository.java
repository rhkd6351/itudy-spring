package com.itudy.api.domain.study.repository;

import com.itudy.api.domain.study.domain.FieldVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<FieldVO, Long> {

    Page<FieldVO> findByNameContains(Pageable pageable, String name);

    boolean existsByName(String name);
}
