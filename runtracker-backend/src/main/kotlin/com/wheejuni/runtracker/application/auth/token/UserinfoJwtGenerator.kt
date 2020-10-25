package com.wheejuni.runtracker.application.auth.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.wheejuni.runtracker.domain.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

const val JWT_SECRET_KEY = "helloruntracker"
const val ISSUER_ID = "runtracker"

@Component
class UserinfoJwtGenerator {

    private val algorithm = Algorithm.HMAC256(JWT_SECRET_KEY)

    fun toJwt(user: User): Mono<String> {
        return Mono.just(JWT.create()
                    .withIssuer(ISSUER_ID)
                    .withClaim("user_id", user.id)
                    .withClaim("user_name", user.nickname)
                    .sign(algorithm))

    }
}