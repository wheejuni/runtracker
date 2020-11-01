package com.wheejuni.runtracker.infra.auth.token;

import com.wheejuni.runtracker.domain.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Set;

public class InApplicationAuthenticationToken extends AbstractAuthenticationToken {

    private Set<? extends GrantedAuthority> authorities;
    private Mono<User> userInfoObject;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public InApplicationAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        super.setAuthenticated(true);
    }

    public InApplicationAuthenticationToken(Set<? extends GrantedAuthority> authorities, Mono<User> userInfoObject) {
        super(authorities);
        super.setAuthenticated(true);

        this.authorities = authorities;
        this.userInfoObject = userInfoObject;
    }

    public InApplicationAuthenticationToken() {
        super(null);
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return userInfoObject;
    }

    @Override
    public Mono<User> getPrincipal() {
        return userInfoObject;
    }
}
