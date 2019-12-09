package io.medgelabs.jwt;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// Filter to check HTTP requests for a valid JWT. If found - shortcuts the Spring Security chain

@AllArgsConstructor
@Slf4j
public class JwtValidationFilter extends OncePerRequestFilter {

  private JwtService jwtService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException, ServletException {

    var jwtHeader = req.getHeader("Authorization");
    if (jwtHeader == null || jwtHeader.isBlank()) {
      log.debug("Authorization header not found");
      chain.doFilter(req, res);
      return;
    }

    jwtService
        .verify(jwtHeader)
        .ifPresent(
            decoded -> {
              // Set the JWT as the Principal. Can be accessed via the @AuthenticationPrincipal
              // annotation
              var auth =
                  new UsernamePasswordAuthenticationToken(
                      decoded, null, List.of(new SimpleGrantedAuthority(decoded.getRole())));
              SecurityContextHolder.getContext().setAuthentication(auth);
            });

    chain.doFilter(req, res);
  }
}
