package io.medgelabs;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.medgelabs.jwt.JwtService;
import io.medgelabs.jwt.JwtValidationFilter;
import io.medgelabs.users.UserApi;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private JwtService jwtService;
  @Autowired private UserApi userApi;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .exceptionHandling()
        // Simpler HTTP 401 response
        .accessDeniedHandler((req, res, ex) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()
        .authorizeRequests()
        .antMatchers("/actuator/**")
        .permitAll()
        .antMatchers("/v1/users/login", "/v1/users/register")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(
            new JwtValidationFilter(jwtService, userApi), BasicAuthenticationFilter.class);
  }
}
