package com.dfsp.dashboard.security;


import com.dfsp.dashboard.User.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.dfsp.dashboard.security.Constans.LOGIN;
import static com.dfsp.dashboard.security.Constans.SIGN_UP_URL;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private UserServiceImpl userService;
    private PasswordEncoder bCryptPasswordEncoder;


    public SecurityConfig(UserServiceImpl userService, PasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated();
              //  .anyRequest().permitAll();

        http
                .headers()
                .frameOptions().sameOrigin()
                .cacheControl();

        http
                .exceptionHandling()
                .accessDeniedPage("/login");

        http
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .formLogin().loginProcessingUrl(LOGIN)
                .and().logout().deleteCookies("JSESSIONID");
    }


//    @Override
//    public void configure(WebSecurity web) throws Exception {
//
//        web
//                .ignoring()
//                .antMatchers(
//                        HttpMethod.POST,
//                        SIGN_UP_URL
//                )
//
//                // allow anonymous resource requests
//                .and()
//                .ignoring()
//                .antMatchers(
//                        //   HttpMethod.GET,
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html"
//                        //   "/**/*.css",
//                        //   "/**/*.js"
//                )
//
//                // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
//                .and()
//                .ignoring()
//                .antMatchers("/h2-console/**/**");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder);

    }


}
