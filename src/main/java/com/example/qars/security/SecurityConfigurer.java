package com.example.qars.security;

import com.example.qars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@EnableGlobalMethodSecurity(jsr250Enabled = true)
@EnableWebSecurity
@Component
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    // Inject user details service & filter
    private final JwtRequestFilter requestFilter;
    private final UserService userService;
    public SecurityConfigurer(
        JwtRequestFilter requestFilter,
        UserService userService
    ){
        this.userService = userService;
        this.requestFilter = requestFilter;
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Session management
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests() // Routes authorization
            // public
                .antMatchers("/login/**").permitAll()
                .antMatchers("/customer/profile/**").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/car/**").permitAll()
                .antMatchers("/customer/upload/**").permitAll()
                .antMatchers("/customer/count/**").permitAll()
                .antMatchers("/booking/**").permitAll()
                .antMatchers("/booking/mybookings/**").permitAll()
                .antMatchers("/establishment/**").permitAll()
                .antMatchers("/admin/license-requests/**").permitAll()
                .antMatchers("/admin/license-response/**").permitAll()
                .antMatchers("/location").permitAll()


                .antMatchers("/api/login/**").permitAll()
                .antMatchers("/api/customer/profile/**").permitAll()
                .antMatchers("/api/register/**").permitAll()
                .antMatchers("/api/car/**").permitAll()
                .antMatchers("/api/customer/upload/**").permitAll()
                .antMatchers("/api/customer/count/**").permitAll()
                .antMatchers("/api/booking/**").permitAll()
                .antMatchers("/api/booking/mybookings/**").permitAll()
                .antMatchers("/api/establishment/**").permitAll()
                .antMatchers("/api/admin/license-requests/**").permitAll()
                .antMatchers("/api/admin/license-response/**").permitAll()
                .antMatchers("/api/location").permitAll()

                // .hasRole("admin")

                .anyRequest().authenticated();

        // Use own request filter
        http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
