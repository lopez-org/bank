package com.mjd.bank.config;

import com.mjd.bank.services.AppUserService;
import com.mjd.bank.services.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final String BEARER = "Bearer ";

  private final JwtService jwtService;
  private final AppUserService appUserService;
  private final HandlerExceptionResolver handlerExceptionResolver;

  public JwtAuthenticationFilter(JwtService jwtService, AppUserService appUserService,
      HandlerExceptionResolver handlerExceptionResolver) {
    this.jwtService = jwtService;
    this.appUserService = appUserService;
    this.handlerExceptionResolver = handlerExceptionResolver;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");
    String jwt;
    String userEmail;

    if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER)) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      jwt = authHeader.substring(BEARER.length());
      userEmail = jwtService.extractUsername(jwt);

      if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = appUserService.userDetailsService().loadUserByUsername(userEmail);

        if (jwtService.isTokenValid(jwt, userDetails)) {
          SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
              userDetails.getAuthorities());
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          securityContext.setAuthentication(authToken);
          SecurityContextHolder.setContext(securityContext);
        }
      }
      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      handlerExceptionResolver.resolveException(request, response, null, ex);
    }
  }
}
