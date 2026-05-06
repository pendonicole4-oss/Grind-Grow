package com.example.grindandgrow.models

data class Habit(
    val name: String,
    val points: Int,
    val isDone: Boolean = false
)