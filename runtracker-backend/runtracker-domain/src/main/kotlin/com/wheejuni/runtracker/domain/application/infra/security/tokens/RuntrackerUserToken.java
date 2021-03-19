package com.wheejuni.runtracker.domain.application.infra.security.tokens;

import com.wheejuni.runtracker.domain.application.model.auth.LoginResult;
import com.wheejuni.runtracker.domain.entity.RuntrackerUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class RuntrackerUserToken extends AbstractAuthenticationToken {

    private RuntrackerUser user;

    public RuntrackerUserToken(RuntrackerUser user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.user = user;
    }

    @Override
    public Object getCredentials() {
        return this.user.getRuntrackerUserId();
    }

    @Override
    public RuntrackerUser getPrincipal() {
        return this.user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }
}
