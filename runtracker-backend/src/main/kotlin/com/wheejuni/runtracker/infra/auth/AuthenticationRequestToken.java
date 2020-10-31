package com.wheejuni.runtracker.infra.auth;

import com.wheejuni.runtracker.application.view.ApplicationLoginRequest;
import com.wheejuni.runtracker.domain.UserInfoProvider;
import lombok.Builder;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Builder
public class AuthenticationRequestToken extends AbstractAuthenticationToken {

    private String username;
    private String credential;
    private UserInfoProvider provider;

    public AuthenticationRequestToken() {
        super(null);
    }

    public static AuthenticationRequestToken fromLoginRequest(ApplicationLoginRequest request) {
        String credential = request.isFormLoginRequest() ? request.getPassword() : request.getAccessToken();

        return AuthenticationRequestToken.builder()
                .username(request.getUsername())
                .provider(request.getProvider())
                .credential(credential)
                .build();
    }

    @Override
    public String getCredentials() {
        return this.credential;
    }

    @Override
    public String getPrincipal() {
        return this.username;
    }
}
