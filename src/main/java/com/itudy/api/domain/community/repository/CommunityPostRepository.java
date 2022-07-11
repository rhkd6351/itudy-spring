package com.itudy.api.domain.community.repository;

import com.itudy.api.domain.community.entity.CommunityPostVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostRepository extends JpaRepository<CommunityPostVO, Long> {

}
