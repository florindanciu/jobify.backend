package com.jobifyProject.jobify.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.jobifyProject.jobify.security.ApplicationUserPermission.JOB_WRITE;
import static com.jobifyProject.jobify.security.ApplicationUserRole.ADMIN;
import static com.jobifyProject.jobify.security.ApplicationUserRole.USER;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/api/v1/jobs/**","/api/v1/companies/**").permitAll()
                .antMatchers("/admin/**").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/api/v1/jobs/**").hasAuthority(JOB_WRITE.name())
                .antMatchers(HttpMethod.POST, "/api/v1/jobs").hasAuthority(JOB_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/v1/jobs/**").hasAuthority(JOB_WRITE.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        // Mock user
        UserDetails annaUser = User.builder()
                .username("anna")
                .password(passwordEncoder.encode("123"))
                .roles(USER.name())
                .build();

        UserDetails adamUser = User.builder()
                .username("adam")
                .password(passwordEncoder.encode("1234"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                annaUser,
                adamUser
        );
    }


}
