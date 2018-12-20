package com.dfsp.dashboard.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(Constans.AUTH_HEADER);

        getHeadersInfo(request, response);

        if (header == null || !header.startsWith(Constans.TOKEN_PREFIX)) {

            chain.doFilter(request, response);

        } else {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
            logger.info(authenticationToken);

            if(authenticationToken != null) {
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                chain.doFilter(request, response);
            }

        }
    }

    //private method //decompile token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        String userToken = null;

        String token = request.getHeader(Constans.AUTH_HEADER);

        if (token != null) {

            try {
                userToken = JWT.require(Algorithm.HMAC512(Constans.SECRET.getBytes()))
                        .build()
                        .verify(token.replace(Constans.TOKEN_PREFIX, ""))
                        .getSubject();
            } catch (JWTVerificationException e) {
                logger.error("the token is expired and not valid anymore");
            }

            logger.info(userToken);

            if (userToken != null) {
                return new UsernamePasswordAuthenticationToken(userToken, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }

    private void getHeadersInfo(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> map = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {

            String key = headerNames.nextElement().toString();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        System.out.println(" * * REQUEST * * * *");
        map.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println(" * * *RESPONSE * * * *");
        Collection<String> headers = response.getHeaders("");
        headers.forEach(System.out::println);
        System.out.println(" * * END * * * *");
    }

}
