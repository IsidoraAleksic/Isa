package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

//        httpSecurity.authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .antMatchers("/authenticate/register").permitAll()
//                .antMatchers("/authenticate/login").permitAll()
//                .and()
        httpSecurity
                .formLogin().disable()
                .httpBasic().disable()
                .rememberMe()
                .and()
                .csrf().disable();



    }


}
