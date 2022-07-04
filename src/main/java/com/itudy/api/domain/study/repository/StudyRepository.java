package com.itudy.api.domain.study.repository;

import com.itudy.api.domain.study.domain.StudyVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<StudyVO, Long> {


}
