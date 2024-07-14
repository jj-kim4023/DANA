//package com.example.dana.common.security.jwt;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@RequiredArgsConstructor
//public class JwtFilter extends OncePerRequestFilter {
//    private final JwtProvider jwtProvider;
//
//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request,
//                                    @NonNull HttpServletResponse response,
//                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
//        String authorization = request.getHeader("Authorization");
//        if (authorization == null || authorization.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        String token = authorization.split(" ")[1];
//
//
//    String username = jwtProvider.getUsername(token);
//    String role = jwtProvider.getRole(token);
//
//    // Entity를 생성하여 값 set
//    Member member = new Member();
//        member.setUsername(username);
//        member.setPassword("temppassword");
//        member.setRole(role);
//
//    // UserDetails에 회원 정보 객체 담기
//    var customUserDetails = new CustomUserDetails(member);
//
//    // 스프링 시큐리티 인증 토큰 생성
//    Authentication authToken;
//    authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
//    // 세션에 사용자 등록
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//
//        filterChain.doFilter(request, response);
//    }
//}
