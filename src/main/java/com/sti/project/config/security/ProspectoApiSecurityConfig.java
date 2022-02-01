package com.sti.project.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProspectoApiSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and().csrf().disable();

        httpSecurity
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();

    }
}
