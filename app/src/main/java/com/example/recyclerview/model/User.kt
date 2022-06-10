package com.example.recyclerview.model
import java.io.Serializable

data class User(
    val id: Long,
    val name: String,
    val company: String,
    val photo: String,
    val logo: String,
): Serializable