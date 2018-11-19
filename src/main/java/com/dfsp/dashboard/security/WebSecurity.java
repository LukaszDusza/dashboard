package com.dfsp.dashboard.security;


import com.dfsp.dashboard.User.UserServiceImpl;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import static com.dfsp.dashboard.security.Constans.LOGIN;
import static com.dfsp.dashboard.security.Constans.SIGN_UP_URL;


@CrossOrigin
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {


    private UserServiceImpl userService;
    private PasswordEncoder bCryptPasswordEncoder;


    public WebSecurity(UserServiceImpl userService, PasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable();

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll();
        http
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))

                //  .anyRequest()
                //  .authenticated()
                //  .anyRequest().permitAll()
                // .antMatchers()
                //  .authenticated()
                //   .and()
                .formLogin().loginProcessingUrl(LOGIN)
                .and().logout().deleteCookies("JSESSIONID");
        //   .and()
        //kazde zapytanie do sciezki zabezpieczonej przechodzi przez filtry
        //zarzadzanie sesjami
        //  .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        // ALWAYS - sesja zostanie utworzona, jelsi jeszcze nie zostala utworzona.
        // ifRequired - sesja zostanie utworzona, w razie potrzeby (default)
        // NEVER - spring nigdy nie utorzy sesji, ale uzyje aktualnej sesji jeśli istnieje.
        // STATELESS - spring nie utworzy ani nie uzyj ezadnej sesji.

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder);
    }


}
