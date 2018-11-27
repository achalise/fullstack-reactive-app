package com.example.kotlinsmple.reposotory

import com.example.kotlinsmple.Article
import com.example.kotlinsmple.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono


interface UserRepository : ReactiveMongoRepository<User, String>, UserCustomRepository {
    fun findByUserId(userid: String): Mono<User>
}

interface ArticleRepository : ReactiveMongoRepository<Article, String>

interface UserCustomRepository {
    fun addArticleToUser(userId: String, vararg articleIds: String)
}

class UserRepositoryImpl: UserCustomRepository {
    override fun addArticleToUser(userId: String, vararg articleIds: String) {
        println("It should add ${articleIds} to user ${userId}")
    }

}