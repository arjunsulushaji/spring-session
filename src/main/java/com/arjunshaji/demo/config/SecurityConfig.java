package com.arjunshaji.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        log.info("INSIDE PASSWORD ENCODER METHOD OF SECURITY CONFIG");
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        log.info("INSIDE AUTHENTICATION MANAGER METHOD OF SECURITY CONFIG");
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("INSIDE CONFIGURE METHOD OF SECURITY CONFIG");
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/admin/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
//
    }
}
