package ru.bankingservices.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bankingservices.service.TokenService;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {
    private final TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI();
        if (path.equals("/api/users/register") || path.equals("/api/users/login") || path.equals("/swagger-ui.html") || path.equals("/swagger-ui/index.html")) {
            chain.doFilter(request, response);
            return;
        }
        String token = httpRequest.getHeader("Authorization");
        if (token == null || !tokenService.validateToken(token)) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Unauthorized: Invalid or missing token");
            return;
        }
        chain.doFilter(request, response);
    }
}
