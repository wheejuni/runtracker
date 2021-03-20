package com.wheejuni.runtracker.api.application.security.tokens.preauthorize;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private String jwt;

    public JwtAuthenticationToken(String jwt) {
        super(null);
        this.jwt = jwt;
    }

    @Override
    public String getCredentials() {
        return this.jwt;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    public boolean isAuthenticationEligible() {
        return !this.jwt.isEmpty();
    }
}
