package com.sora.springboot.config.auth;

import com.sora.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * @EnableWebSecurity
 *      : Spring Security 설정들을 활성화한다.
 * csrf()
 *      : CSRF(Cross Site Request Forgery - 사이트간 요청 위조) 란?
 *              웹 애플리케이션 취약점 중 하나로
 *              사용자가 자신의 의지와 무관하게 공격자가 의도한 행동을 하여 특정 웹 페이지를 보안에 취약하게 하거나
 *              혹은 수정, 삭제 등의 작업을 하게 만드는 공격 방법이다.
 *              특정 사이트가 사용자의 브라우저를 신뢰한다는 점을 공격한다. (악성코드가 서버에서 발생된다.)
 * headers().frameOptions().disable()
 *      : X-Frame-Options 를 비활성화한다.
 *        보안적인 이슈가 발생할 수 있다.
 * csrf().disable().headers().frameOptions().disable()
 *      : h2-console 화면을 사용하기 위해 해당 옵션들은 비활성화 해야 한다.
 * authorizeRequests
 *      : URL 별 권한 관리를 설정하는 옵션의 시작점이다.
 *        antMatchers 옵션을 사용할 수 있다.
 * antMatchers
 *      : 권한 관리 대상을 지정하는 옵션이다.
 *        URL, HTTP 메소드 별로 관리가 가능하다.
 *        (아래) 지정된 URL 들은 permitAll() 옵션을 통해 전체 열람 권한을 주었다.
 *        (아래) "/api/v1/**" 주소를 가진 API 는 USER 권한을 가진 사람만 가능하도록 했다.
 * anyRequest
 *      : 설정된 값들 이외 나머지 URL 들을 나타낸다.
 *        (아래) authenticated() 를 추가하여 나머지 URL 들은 모두 인증된 사용자들에게만 허용하게 했다.
 * logout().logoutSuccessUrl("/")
 *      : 로그아웃 기능에 대한 여러 설정의 진입점이다.
 *        로그아웃 성공 시 지정한 주소로 이동한다.
 * oauth2Login
 *      : OAuth2 로그인 기능에 대한 여러 설정의 진입점이다.
 * userInfoEndpoint
 *      : OAuth2 로그인 성공 이수 하용자 정보를 가져올 때의 설정들을 담당한다.
 * userService
 *      : 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다.
 *        리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.
 */
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/.**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }
}
