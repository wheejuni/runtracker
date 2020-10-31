package com.wheejuni.runtracker.infra.auth;

import com.wheejuni.runtracker.domain.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class InApplicationAuthenticationToken extends AbstractAuthenticationToken {

    private Set<? extends GrantedAuthority> authorities;
    private User userInfoObject;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public InApplicationAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public InApplicationAuthenticationToken(Set<? extends GrantedAuthority> authorities, User userInfoObject) {
        super(authorities);
        this.authorities = authorities;
        this.userInfoObject = userInfoObject;
    }

    @Override
    public Object getCredentials() {
        return userInfoObject.getCredential();
    }

    @Override
    public User getPrincipal() {
        return userInfoObject;
    }
}
