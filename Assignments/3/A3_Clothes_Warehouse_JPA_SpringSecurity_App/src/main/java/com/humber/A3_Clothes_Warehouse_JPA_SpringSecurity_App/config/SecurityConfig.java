package com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.config;

import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.services.myUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // it is a config class
@EnableWebSecurity

public class SecurityConfig {
    // inject myUserDetailService into here Security Config
    private final myUserDetailService userDetailsService;
    public SecurityConfig(myUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain springSecurityFilterChain2(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/warehouse/home", "/login/**", "/registering/**", "/static/**").permitAll()
                .requestMatchers("/warehouse/inventory/**").hasAnyRole("ADMIN","EMPLOYEE")
                .requestMatchers("/warehouse/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()

        ).formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer
                    .loginPage("/login")
                    .defaultSuccessUrl("/warehouse/home", true)
                    .permitAll();
        })
                .logout( logout -> {
                    logout.logoutUrl("/logout")
                            .permitAll();
                });
        return http.build();
        // All Filter Chain
    }
    // loads data from enter user allows access
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // Data access object // repo layer special type //only deals with User info
        //1. user details service (user)
        provider.setUserDetailsService(userDetailsService);
        //2. Password Encoder
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    //encriypating password
    @Bean public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebSecurityCustomizer ignoreResources() {
        return (webSecurity) -> webSecurity
                .ignoring()
                .requestMatchers("/css/**", "/h2-console/**");
    }
}


    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("emp1").password(passwordEncoder().encode("1")).roles("EMPLOYEE").build();
        UserDetails admin1= User.withUsername("manag2").password(passwordEncoder().encode("1")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1,admin1);
    }*/
