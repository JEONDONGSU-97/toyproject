package My.toyproject.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.ProviderManagerBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AuthenticationFailureHandler authenticationFailureHandler;

    //인증을 처리하는 여러개의 시큐리티 필터를 담는 체인.
    //또, 필터체인 프록시(전 단계 필터)를 통해 서블릿 필터와 연결되고 어떤 시큐리티 필터를 통해 인증을 수행하는지 결정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll()
                .and()
                .csrf().disable();
        http.formLogin()
                .loginPage("/member/login")
                .failureHandler(authenticationFailureHandler)
                .defaultSuccessUrl("/")
            .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/") //로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true); //로그아웃 성공시 세션 삭제
//        http.sessionManagement()
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true)
//                .and().invalidSessionUrl("/");
//        http.sessionManagement().sessionFixation().changeSessionId();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);


        return http.build();
    }

    //스프링 시큐리티의 인증 담당
    //AuthenticationManager 빈 생성시 스프링 내부 동작으로 인해 작성한 UserDetailsServiceImpl, PasswordEncoder 자동 설정
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
