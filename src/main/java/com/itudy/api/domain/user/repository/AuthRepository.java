package com.itudy.api.domain.user.repository;


import com.itudy.api.domain.user.domain.AuthVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AuthRepository 클래스
 * Spring data jpa를 사용
 */
@Repository
public interface AuthRepository extends JpaRepository<AuthVO, String> {

}
