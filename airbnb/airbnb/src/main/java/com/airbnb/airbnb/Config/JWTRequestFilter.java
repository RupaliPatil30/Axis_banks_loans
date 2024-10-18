package com.airbnb.airbnb.Config;

import ch.qos.logback.core.net.SMTPAppenderBase;
import com.airbnb.airbnb.Repository.PropertyUserRepository;
import com.airbnb.airbnb.entity.PropertyUser;
import com.airbnb.airbnb.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static java.nio.file.Files.setAttribute;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    private PropertyUserRepository userRepository;
    private Object Credentials;

    public JWTRequestFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(8, tokenHeader.length() - 1);
            String username = jwtService.getUsername(token);
            Optional<PropertyUser> opUser = userRepository.findByUsername(username);
            if (opUser.isPresent()) {
       PropertyUser user=opUser.get();

          //to keep track of current user login



                UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
                authentication.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);


            }

            filterChain.doFilter(request, response);
        }



    }
}
