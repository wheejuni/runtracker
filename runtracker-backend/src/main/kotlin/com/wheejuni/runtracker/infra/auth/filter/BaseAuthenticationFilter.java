package com.wheejuni.runtracker.infra.auth.filter;

import com.wheejuni.runtracker.infra.auth.exception.UriDoesNotMatchException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;
import reactor.core.publisher.Mono;

public class BaseAuthenticationFilter implements WebFilter, InitializingBean {

    private final PathPattern pattern;
    private final ServerAuthenticationConverter authenticationConverter;
    private final ReactiveAuthenticationManager authenticationManager;
    private final ServerAuthenticationSuccessHandler successHandler;
    private final ServerAuthenticationFailureHandler failureHandler;
    private final ServerSecurityContextRepository securityContextRepository = NoOpServerSecurityContextRepository.getInstance();

    private PathPatternParserServerWebExchangeMatcher exchangeMatcher;

    public BaseAuthenticationFilter(
            PathPattern pattern,
            ServerAuthenticationConverter authenticationConverter,
            ReactiveAuthenticationManager authenticationManager,
            ServerAuthenticationSuccessHandler successHandler, ServerAuthenticationFailureHandler failureHandler) {

        Assert.notNull(pattern, "filter pattern should be provided!");

        this.pattern = pattern;
        this.authenticationConverter = authenticationConverter;
        this.authenticationManager = authenticationManager;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.exchangeMatcher = new PathPatternParserServerWebExchangeMatcher(this.pattern);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        this.exchangeMatcher.matches(exchange)
                .filter(ServerWebExchangeMatcher.MatchResult::isMatch)
                .flatMap(result -> this.authenticationConverter.convert(exchange))
                .switchIfEmpty(chain.filter(exchange).then(Mono.empty()))
                .flatMap(authenticationManager::authenticate)
                .onErrorResume(AuthenticationException.class, err ->
                        this.failureHandler.onAuthenticationFailure(new WebFilterExchange(exchange, chain), err))


        return null;
    }

    private Mono<Void> authenticate(Authentication token) {
        return
    }
}
