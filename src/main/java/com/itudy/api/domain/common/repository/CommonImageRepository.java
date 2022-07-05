package com.itudy.api.domain.common.repository;


import com.itudy.api.domain.common.entity.CommonImageVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommonImageRepository extends JpaRepository<CommonImageVO, Long> {
    Optional<CommonImageVO> findByName(String name);
}
