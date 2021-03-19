package com.wheejuni.runtracker.domain.application.infra.security.handlers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wheejuni.runtracker.domain.application.infra.security.tokens.RuntrackerUserToken;
import com.wheejuni.runtracker.domain.application.model.auth.LoginResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;


/**
 * Bomeehouse studios (me@wheejuni.com)
 * 2021/03/19
 */
public class ApiAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
    private static final Logger log = LoggerFactory.getLogger(ApiAuthenticationSuccessHandler.class);

    private ObjectMapper defaultMapper;

    public ApiAuthenticationSuccessHandler(ObjectMapper defaultMapper) {
        this.defaultMapper = defaultMapper;
    }

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        RuntrackerUserToken authenticatedToken = (RuntrackerUserToken)authentication;
        String token = null;

        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userid", String.valueOf(authenticatedToken.getPrincipal().getRuntrackerUserId()));

            Algorithm alg = Algorithm.HMAC256("secret");

            token = JWT.create().withHeader(claims).sign(alg);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        LoginResult result = new LoginResult("success", token, "refresh");
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();

        response.setStatusCode(HttpStatus.OK);
        generateResponseBody(response, result);

        return Mono.empty();
    }

    private Mono<DataBuffer> generateResponseBody(ServerHttpResponse response, LoginResult result) {
        Mono<String> serializedValue = null;

        try {
            serializedValue = Mono.just(this.defaultMapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            log.error("error while processing JSON", e);
        }

        return serializedValue.map(sv -> response.bufferFactory().wrap(sv.getBytes()));
    }
}
