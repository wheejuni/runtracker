package com.wheejuni.runtracker.api.application.security.tokens;

import com.wheejuni.runtracker.domain.application.model.auth.SocialProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Bomeehouse studios (me@wheejuni.com)
 * 2021/03/14
 */
public abstract class SocialProviderLoginAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String token;

    public SocialProviderLoginAuthenticationToken(String token) {
        super(token, token);

        if (token == null) {
            throw new IllegalArgumentException("token must be provided");
        }
        this.token = token;
    }

    @Override
    public String getCredentials() {
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    abstract public SocialProvider getRequestedAuthenticationProvider();
}
