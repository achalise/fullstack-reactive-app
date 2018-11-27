package com.example.kotlinsmple.routes

import com.example.kotlinsmple.handlers.LoginHandler
import com.example.kotlinsmple.reposotory.ArticleRepository
import com.example.kotlinsmple.reposotory.UserRepository
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router

@Configuration
class WebConfiguration {
    @Bean
    fun routes(userRepository: UserRepository, articleRepository: ArticleRepository, loginHandler: LoginHandler) = router {
        GET("/api/users") { ServerResponse.ok().body(userRepository.findAll()) }
        GET("/api/articles") { ServerResponse.ok().body(articleRepository.findAll())}
        POST("/api/login") { loginHandler.login(it) }
        GET("/api/")
    }

    @Bean
    fun gateway(rlb: RouteLocatorBuilder) = rlb.routes {
        route {
            path("/gateway")
            uri("http://spring.io:80/guides")
        }
    }
}



