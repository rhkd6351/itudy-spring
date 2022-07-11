package com.itudy.api.domain.community.repository;

import com.itudy.api.domain.community.entity.CommunityCommentVO;
import com.itudy.api.domain.community.entity.CommunityPostVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCommentRepository extends JpaRepository<CommunityCommentVO, Long> {

    Page<CommunityCommentVO> findByPost(CommunityPostVO post, Pageable pageable);
}
