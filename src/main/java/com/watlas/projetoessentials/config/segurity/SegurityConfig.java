package com.watlas.projetoessentials.config.segurity;

import com.watlas.projetoessentials.service.WatlasUserService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SegurityConfig extends WebSecurityConfigurerAdapter {

    private final WatlasUserService watlasUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()  //desabilita o token para fazer post
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()//habilita o token para fazer post
                .authorizeRequests()
                .antMatchers("/animes/admin/**").hasRole("ADMIN")  //proteger a URL desejada
                .antMatchers("/animes/**").hasRole("USER")
                .antMatchers("/actuator/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin() //tela de login
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder p = PasswordEncoderFactories.createDelegatingPasswordEncoder();
       log.info("Password enconded {}", p.encode("test"));
        auth.inMemoryAuthentication()
                .withUser("watlas2")
                .password(p.encode("watlas2"))
                .roles("USER, ADMIN").and() //tipo do usuario
                .withUser("dev2") //no do usuario
                .password(p.encode("dev2")) //senha
                .roles("USER");  //roles tipo de usuario

        auth.userDetailsService(watlasUserService).passwordEncoder(p);
    }


}
