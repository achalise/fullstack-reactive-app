package com.example.kotlinsmple

import com.example.kotlinsmple.reposotory.ArticleRepository
import com.example.kotlinsmple.reposotory.UserRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.util.*

@SpringBootApplication
class KotlinSmpleApplication

fun main(args: Array<String>) {
    runApplication<KotlinSmpleApplication>(*args)
}


@Component
class DataWriter(val userRepository: UserRepository, val articleRepository: ArticleRepository) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {


        articleRepository.deleteAll().subscribe()
        userRepository.deleteAll().thenMany(
                Flux.just("user1", "user2", "user3")
                        .map { User(firstName = it, userId = it, articles = emptyList()) }
                        .flatMap { userRepository.save(it) }
                        .map { testData[it.userId]!! }
                        .flatMap { articleRepository.save(it) }
                        .doOnNext {
                            println(" The article id is ${it.id})")
                            userRepository.addArticleToUser(it.userId!!, it.id!!);
                        }
        ).thenMany(
                userRepository.findAll().map { println("The user $it") }
        ).thenMany(
                articleRepository.findAll().map { println("The article $it") }
        ).thenMany(
                userRepository.findByUserId("user1").map { println("The user by userid $it") }
        ).subscribe()
    }
}

val testData = hashMapOf(
        "user1" to Article(title = "Man must explore, and this is exploration at its greatest",
                summary = "Problems look mighty small from 150 miles up", createdOn = Date(), userId = "user1"),
        "user2" to Article(title = "Science has not yet mastered prophecy",
                summary = "We predict too much for the next year and yet far too little for the next ten.",
                createdOn = Date(), userId = "user2"),
        "user3" to Article(title = "Failure is not an option",
                summary = "Many say exploration is part of our destiny, but it’s actually our duty to future generations.",
                createdOn = Date(), userId = "user3")
)

