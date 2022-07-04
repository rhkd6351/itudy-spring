package com.itudy.api.domain.study.repository;

import com.itudy.api.domain.study.domain.StudyVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<StudyVO, Long> {


    @EntityGraph(attributePaths = {"fieldMappings", "fieldMappings.field", "members", "members.user" })
    Page<StudyVO> findByNameContains(Pageable pageable, String name);
}
