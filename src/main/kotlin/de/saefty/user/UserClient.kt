package de.saefty.user

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserClient {
    fun getAllUsers(): List<User> = listOf()
    fun getUserById(userId: String): User = User("")
}