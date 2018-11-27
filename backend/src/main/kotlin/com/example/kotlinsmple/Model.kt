package com.example.kotlinsmple

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class User(var id: String? = null, var userId: String? = null, var firstName: String? = null,
                var lastName: String? = null, var articles: List<String>)


@Document
data class Article(var id: String? = null, var userId: String? = null, var title: String? = null,
                   var createdOn: Date? = null, var summary: String? = null)

data class LoginInfo(val userId: String? = null, val password: String? = null)


data class Clap(var articleId: String)
