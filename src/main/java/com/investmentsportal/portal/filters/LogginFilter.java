//package com.investmentsportal.portal.filters;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class LogginFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request, HttpServletResponse response,
//            FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("OncePerRequestFilter" );
//        filterChain.doFilter(request,response);
//        System.out.println("response "+ response.getStatus() );
//    }
//}