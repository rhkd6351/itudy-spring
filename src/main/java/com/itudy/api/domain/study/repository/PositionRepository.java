package com.itudy.api.domain.study.repository;

import com.itudy.api.domain.study.domain.PositionVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<PositionVO, String> {


}
