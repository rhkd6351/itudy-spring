package com.itudy.api.jwt;

import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.repository.UserRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Spring security에서 UserDetailsService의 커스텀 구현체
 */
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final String credentialPassword;

    public CustomUserDetailsService(UserRepository userRepository,
                                    @Value("${jwt.credential}") String credentialPassword) {
        this.userRepository = userRepository;
        this.credentialPassword = credentialPassword;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) {
        return userRepository.findByEmail(email)
                .map(this::createUser)
                .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    @Transactional
    org.springframework.security.core.userdetails.User createUser(UserVO user) {
        if (!user.isActivated()) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION_NOT_ACTIVATED);
        }
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getAuth().getName()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                credentialPassword,
//                "user.getPassword()",
                grantedAuthorities);
    }
}