package com.itudy.api.common.repository;


import com.itudy.api.common.entity.CommonImageVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommonImageRepository extends JpaRepository<CommonImageVO, Long> {
    Optional<CommonImageVO> findByName(String name);
}
