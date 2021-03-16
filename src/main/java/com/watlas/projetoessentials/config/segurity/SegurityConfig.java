package com.watlas.projetoessentials.config.segurity;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@Log4j2
public class SegurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder p = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password enconded {}", p.encode("test"));
        auth.inMemoryAuthentication()
                .withUser("watlas")
                .password(p.encode("watlas"))
                .roles("USER, ADMIN").and()
                .withUser("dev")
                .password(p.encode("dev"))
                .roles("USER, ADMIN");
    }

}
