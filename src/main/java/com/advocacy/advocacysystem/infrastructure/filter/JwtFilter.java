package com.advocacy.advocacysystem.infrastructure.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advocacy.advocacysystem.infrastructure.config.JwtTokenConfig;
import com.advocacy.advocacysystem.infrastructure.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenConfig jwtTokenConfig;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        // Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) /* || !header.startsWith("Bearer ")*/) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = header; //header.split(" ")[1].trim();

        // Get user identity and set it on the spring security context
        UserDetails userDetails = userService.getUserByUserName(jwtTokenConfig.getUsernameFromToken(token));

        if (!jwtTokenConfig.validateToken(token, userDetails)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                        List.of() : userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}