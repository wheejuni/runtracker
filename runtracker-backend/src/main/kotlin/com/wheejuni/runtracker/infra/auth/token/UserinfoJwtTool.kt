package com.wheejuni.runtracker.infra.auth.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.impl.NullClaim
import com.auth0.jwt.interfaces.Claim
import com.wheejuni.runtracker.application.auth.UserinfoManagementService
import com.wheejuni.runtracker.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

const val JWT_SECRET_KEY = "helloruntracker"
const val ISSUER_ID = "runtracker"

@Component
class UserinfoJwtTool(private val infoService: UserinfoManagementService) {

    private val algorithm = Algorithm.HMAC256(JWT_SECRET_KEY)

    fun toJwt(user: User): Mono<String> {
        return Mono.just(JWT.create()
                    .withIssuer(ISSUER_ID)
                    .withClaim("user_id", user.id)
                    .withClaim("user_name", user.nickname)
                    .withClaim("user_authority", "RUNNER")
                    .sign(algorithm))

    }

    fun fromJwt(jwt: String): Mono<InApplicationAuthenticationToken> {
        val decoded = JWT.decode(jwt)
        val userId = decoded.claims["user_id"] ?: NullClaim()
        val userAuthorityClaim = decoded.claims["user_authority"] ?: NullClaim()

        val user = infoService.findByUserId(userId.asLong())
        val userAuthority = mutableSetOf<GrantedAuthority>(SimpleGrantedAuthority(userAuthorityClaim.asString()))

        if (decoded.signature != JWT_SECRET_KEY) {
            throw SecurityException("signature does not match")
        }

        return Mono.just(InApplicationAuthenticationToken(userAuthority, user))
    }
}