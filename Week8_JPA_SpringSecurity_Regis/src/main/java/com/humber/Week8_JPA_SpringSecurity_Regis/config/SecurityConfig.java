package com.humber.Week8_JPA_SpringSecurity_Regis.config;

import com.humber.Week8_JPA_SpringSecurity_Regis.services.myUserDetailService;
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

@Configuration // a config class
@EnableWebSecurity // will not work if not there // tells sec to create // we are implementing interface
public class SecurityConfig {
    //injecting myUserDetailService into Security Config //constructor injection
    private final myUserDetailService userDetailsService;
    public SecurityConfig(myUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // make a BEAN anything inside
    @Bean
    public SecurityFilterChain springSecurityFilterChain1(HttpSecurity  http) throws Exception { //  HttpSecurity (class )
        // http (instance)

        http.authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers("/restaurant/home", "login/**", "registers/**", "/", "/css/**").permitAll()
                                .requestMatchers("/restaurant/menu/**").hasAnyRole("USER", "ADMIN")
                                // /** dosn't matter anylevel go down still works any sub level
                                .requestMatchers("/restaurant/admin/**").hasRole("ADMIN") // need ADMIN to login
                                // ** any sub endpoints works too
                                // 3 Rules to LOGIN access
                                .anyRequest().authenticated() // any other request, other than 3 need authentication // see login
                        // need form for error , not see login
                ).formLogin(httpSecurityFormLoginConfigurer -> { //custom login
                    httpSecurityFormLoginConfigurer
                            .loginPage("/login")
                            .defaultSuccessUrl("/restaurant/home", true)
                            .permitAll();
                })      // have to spring seruity  to use our login page //http.authorizeHttpRequests method
                .logout(l -> {
                                     l.logoutUrl("/logout")
                                    //.logoutSuccessUrl("/restaurant/home") //do not need redirect to login
                                    .permitAll();
                });
        // above what form is it use that one // no custom one use Springs
        return http.build();
        // All Filter Chain
    }
    /*@Bean
    public UserDetailsService userDetailsService1() {
        //creating a bunch of users
        UserDetails user1 = User.withUsername("user").password(passwordEncoder1().encode("humber"))
                .roles("USER").build(); // First User // roles should same as above UPPER CASE
        UserDetails admin1 = User.withUsername("admin").password(passwordEncoder1().encode("humber"))
                .roles("ADMIN").build(); // Second User
        UserDetails user2 = User.withUsername("nikola95").password(passwordEncoder1().encode("tata123"))
                .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1, user2, admin1);
    }*/

    //load user by username, loads data
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // Data access object // repo layer special type //only deals with User info
        //1) user details service
            provider.setUserDetailsService(userDetailsService);

        // 2) Password encoder
        provider.setPasswordEncoder(passwordEncoder1());


        return provider;
    }


    //encriypating password
    @Bean
    public BCryptPasswordEncoder passwordEncoder1() {
        return new BCryptPasswordEncoder(); // return class // use this to encode the password
    } /// until method

    @Bean
    //to ignore css, h2 console from authentication, so do not need to log in to view/use
    public WebSecurityCustomizer ignoreResources1() {
        return (webSecurity) -> webSecurity
                .ignoring()
                .requestMatchers("/css/**", "/h2-console/**");
    }
}// class