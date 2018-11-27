package com.example.kotlinsmple.handlers

import com.example.kotlinsmple.LoginInfo
import com.example.kotlinsmple.reposotory.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class LoginHandler {
    val userRepository: UserRepository

    constructor(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    fun login(serverRequest: ServerRequest): Mono<ServerResponse> {
        val loginInfo = serverRequest.bodyToMono(LoginInfo::class.java)

        return loginInfo.flatMap { this.userRepository.findByUserId(it.userId!!) }
                .flatMap { ServerResponse.ok().body(Mono.just(it)) }
                .switchIfEmpty( ServerResponse.status(HttpStatus.NOT_FOUND).body(Mono.just("User not found")))

    }
}
// curl -d '{"userId": "user1", "password": "password"}' -H "Content-Type: application/json" -X POST "http://localhost:8080/api/login"
