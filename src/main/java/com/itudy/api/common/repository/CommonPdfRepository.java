package com.itudy.api.common.repository;


import com.itudy.api.common.entity.CommonPdfVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommonPdfRepository extends JpaRepository<CommonPdfVO, Long> {
    Optional<CommonPdfVO> findByName(String name);
}
