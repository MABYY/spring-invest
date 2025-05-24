package com.investmentsportal.portal.filters;

import com.investmentsportal.portal.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {


    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");

        if (authHeader == null || ! authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response); // pass the request to the next filter in the chain
            // let spring security give access or not to the endoint requested
            return;
        }

        // Otherwise get the token and validate it to decide if it should pass to the next filter in the chain
        var token = authHeader.substring(7);

        System.out.println("1 wtService.validateToken(token) "+ jwtService.validateToken(token));
        if(!jwtService.validateToken(token)){
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("2 wtService.validateToken(token) "+ jwtService.validateToken(token));

        //  Token is Valid -> Notify Spring so that user gets access to restricted endpoints
        // Create authentication object
//        var role = jwtService.getRoleFromToken(token);
        var userId =  jwtService.getUserIdFromToken(token);
        var authentication = new UsernamePasswordAuthenticationToken(
                userId, null, null
////                List.of(new SimpleGrantedAuthority("ROLE_" + role))
////                jwtService.getEmailFromToken(token),null,null
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
