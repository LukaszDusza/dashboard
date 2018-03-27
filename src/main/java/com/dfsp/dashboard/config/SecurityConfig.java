package com.dfsp.dashboard.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";

    @Override
    public void configure(HttpSecurity httpSecurity) {
        try {
            httpSecurity.authorizeRequests()
                    .antMatchers("/")
                    .permitAll()
                    //  .antMatchers("/api/user/*").hasRole(USER)
                    // .antMatchers("/api/admin/*").hasRole(ADMIN)
                    //.and()
                    .anyRequest() //zamist tergo co na powyzej
                    .authenticated() //zamist tego co powyzej
                    .and() //nie potrzebne gdy gora odkomentowana
                    .formLogin();

            httpSecurity.csrf().disable(); //odblokowuje POST request (403 forbidden)

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth.inMemoryAuthentication()
                    .withUser("user")
                    .password("{noop}user")
                    .roles(USER)
                    .and()
                    .withUser("admin")
                    .password("{noop}admin")
                    .roles(USER, ADMIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
