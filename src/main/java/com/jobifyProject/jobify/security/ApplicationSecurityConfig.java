package com.jobifyProject.jobify.security;

import com.jobifyProject.jobify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

import static com.jobifyProject.jobify.security.ApplicationUserRole.ADMIN;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf().disable()
               .authorizeRequests()
                .antMatchers("/","/api/v1/jobs/**","/api/v1/users/**","/api/v1/companies/**").permitAll()
                .antMatchers("/admin/**").hasRole(ADMIN.name())
//                .antMatchers(HttpMethod.DELETE, "/api/v1/jobs/**").hasAuthority(JOB_WRITE.name())
//                .antMatchers(HttpMethod.POST, "/api/v1/jobs").hasAuthority(JOB_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/api/v1/jobs/**").hasAuthority(JOB_WRITE.name())
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
        List<com.jobifyProject.jobify.model.User> users = userRepository.findAll();

        UserDetails gabiUser = User.builder()
                .username(users.get(0).getUsername())
                .password(passwordEncoder.encode(users.get(0).getPassword()))
                .roles(users.get(0).getRole())
                .build();

//        UserDetails adamUser = User.builder()
//                .username(users.get(1).getUsername())
//                .password(passwordEncoder.encode(users.get(1).getPassword()))
//                .roles(users.get(1).getRole())
//                .build();

        return new InMemoryUserDetailsManager(
                gabiUser
        );
    }



}
