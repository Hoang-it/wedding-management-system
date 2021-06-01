package com.wms;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostConstruct
    public void completeSetup(){
        customUserDetailsService = applicationContext.getBean(CustomUserDetailsService.class);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] allowAll  =  {
            "/css/**",
            "/images/**",
            "/fonts/**",
            "/scripts/**",
            "/ds/**",
            "/api/**",
            "/tra-cuu-tiec-cuoi",
        };

        

        http.authorizeRequests()
            .antMatchers(allowAll).permitAll()
            .antMatchers("/").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .failureUrl("/login-error")
            .successHandler(new CustomeLoginSuccessHandler())
            .permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/access-denied")
            .and()
            .logout()
            .permitAll()
            .logoutSuccessUrl("/login")
            .and()
            .rememberMe().key("uniqueAndSecret").tokenValiditySeconds(60 * 2)
            .and()
            .csrf().disable();

    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());
        
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
