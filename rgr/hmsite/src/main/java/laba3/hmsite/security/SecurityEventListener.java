package laba3.hmsite.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class SecurityEventListener {

    private static final Logger logger = LoggerFactory.getLogger(SecurityEventListener.class);

    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        String username = principal != null ? event.getAuthentication().getName() : "unknown";
        logger.info("Authentication success for user: {}", username);
    }

    @EventListener
    public void handleAuthenticationFailure(AbstractAuthenticationFailureEvent event) {
        String username = event.getAuthentication() != null ? event.getAuthentication().getName() : "unknown";
        Throwable ex = event.getException();
        String exClass = ex != null ? ex.getClass().getSimpleName() : "UnknownException";
        String exMsg = ex != null ? ex.getMessage() : "(no message)";

        logger.warn("Authentication failure for user='{}'", username);
    }
}
