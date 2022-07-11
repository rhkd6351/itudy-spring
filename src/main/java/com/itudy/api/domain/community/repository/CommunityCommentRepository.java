package com.itudy.api.domain.community.repository;

import com.itudy.api.domain.community.entity.CommunityCommentVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCommentRepository extends JpaRepository<CommunityCommentVO, Long> {

}
