package com.example.qars.security;

import com.example.qars.entity.User;
import com.example.qars.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Request filter, checks filter every request  **/
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    // Keeps track of guest accounts
    private List<User> guestAccounts = new ArrayList<>();

    /** Services & Components **/
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public JwtRequestFilter(
        UserService userService,
        JwtUtil jwtUtil
    ){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        // Declare vars
        String jwt = null;
        String username = null;

        // Attempt to get token from header
        final String authorizationHeader = request.getHeader("Authorization");

        // Check if key Authorization in the header contains a String containing Bearer
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            // Get JWT token without "Bearer "
            jwt = authorizationHeader.substring(7);
            // Extract username from token with jwtUtil
            username = jwtUtil.extractUsername(jwt);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            User user = null;

            try{ // Attempt to find account with service
                user = this.userService.loadUserByUsername(username);
            }catch(UsernameNotFoundException e){
                // Attempt to find user
                for(User guestAccount : guestAccounts){
                    if(guestAccount.getUsername().equals(username)){
                        user = guestAccount;
                    }
                }
            }

            // If account has been found, validate token with jwt util
            if(user != null && jwtUtil.validateToken(jwt, user)){
                // Create Spring Security authentication token
                UsernamePasswordAuthenticationToken userToken =
                        new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userToken);

            // Else throw username not found
            }else throw new UsernameNotFoundException(username);
        }

        // Pass request & response
        filterChain.doFilter(request, response);
    }

    public List<User> getGuestAccounts(){ return this.guestAccounts; }
    public void setGuestAccounts(List<User> accounts){ this.guestAccounts = accounts; }
}
