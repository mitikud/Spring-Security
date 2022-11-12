package com.security.inmemory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.jnlp.UnavailableServiceException;
import java.util.ArrayList;
import java.util.List;

public class SecurityConfig2 {

    @Bean
    protected InMemoryUserDetailsManager configAuthentication(){
        List<UserDetails> users = new ArrayList<>();
        List<GrantedAuthority> adminAuthority = new ArrayList<>();
        adminAuthority.add(new SimpleGrantedAuthority("ADMIN"));
        UserDetails admin = new User("devs","{noop}devs",adminAuthority);
        users.add(admin);

        List<UserDetails> employeeAutority = new ArrayList<>();
        adminAuthority.add(new SimpleGrantedAuthority("EMPLOYEE"));
        UserDetails employee = new User("ns","{noop}ns",adminAuthority);
        users.add(employee);

        List<UserDetails> managerAuthority = new ArrayList<>();
        adminAuthority.add(new SimpleGrantedAuthority("MANAGER"));
        UserDetails manager = new User("vs","{noop}vs",adminAuthority);
        users.add(manager);

        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //declares which Page(URL) will have What access type
        http.authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers("/welcome").authenticated()
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/emp").hasAuthority("EMPLOYEE")
                .antMatchers("/mgr").hasAuthority("MANAGER")
                .antMatchers("/common").hasAnyAuthority("EMPLOYEE","MANAGER")

                // Any other URLs which are not configured in above antMatchers
                // generally declared aunthenticated() in real time
                .anyRequest().authenticated()

                //Login Form Details
                .and()
                .formLogin()
                .defaultSuccessUrl("/welcome", true)

                //Logout Form Details
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                //Exception Details
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied");

        return http.build();

    }
}
