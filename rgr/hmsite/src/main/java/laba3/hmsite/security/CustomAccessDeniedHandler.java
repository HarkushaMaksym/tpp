package laba3.hmsite.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String user = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "anonymous";
        String path = request.getRequestURI();
        logger.warn("Access denied for user='{}' on path='{}' - message={}", user, path, accessDeniedException.getMessage());
        response.sendRedirect(request.getContextPath() + "/access-denied");
    }
}
