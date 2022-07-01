package com.itudy.api.config;

import com.itudy.api.jwt.JwtAccessDeniedHandler;
import com.itudy.api.jwt.JwtAuthenticationEntryPoint;
import com.itudy.api.jwt.JwtSecurityConfig;
import com.itudy.api.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;


/**
 * Spring Security 설정
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    private final TokenProvider tokenProvider;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
//    private final CustomOAuth2UserService customOAuth2UserService;
//    private final OauthCustomSuccessHandler oauthCustomSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //csrf 사용안함
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 사용안함
                .and()
                .addFilter(corsFilter)
                .formLogin().disable() //폼태그 로그인 사용안함
                .httpBasic().disable(); //http Authorization id,pw 인증방식 제거

        http.authorizeRequests()
                //로그인
                .antMatchers(HttpMethod.GET,"/api/v1/oauth/**")
                .permitAll()

                .anyRequest().authenticated();

        //JWT 설정
        http
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

//        http
//                .oauth2Login()
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService)
//                .and()
//                .defaultSuccessUrl("/api/v1/login/success");


    }
}
