package com.example.grindandgrow.models

data class Habit(
    val name: String,
    val isDone: Boolean = false,
    val category: String
)