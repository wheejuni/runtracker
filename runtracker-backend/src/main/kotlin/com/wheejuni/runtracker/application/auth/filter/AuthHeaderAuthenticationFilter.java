package com.wheejuni.runtracker.application.auth.filter;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class AuthHeaderAuthenticationFilter implements WebFilter {

    private final ReactiveAuthenticationManager authenticationManager;
    private final ServerAuthenticationSuccessHandler successHandler;
    private final ServerAuthenticationFailureHandler failureHandler;

    public AuthHeaderAuthenticationFilter(
            ReactiveAuthenticationManager authenticationManager,
            ServerAuthenticationSuccessHandler successHandler, ServerAuthenticationFailureHandler failureHandler) {
        this.authenticationManager = authenticationManager;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return null;
    }
}
