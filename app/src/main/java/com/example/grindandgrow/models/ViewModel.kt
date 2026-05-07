package com.example.grindandgrow.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    var habits = mutableStateListOf<Habit>()

    var transactions = mutableStateListOf<Transaction>()

    fun addHabit(name: String, isDone: Boolean = false, category: String) {
        habits.add(Habit(name,  isDone, category))
    }

    fun toogleHabit(index: Int) {
        val habit = habits[index]
        habits[index] = habit.copy(isDone = !habit.isDone)
    }

    fun addTransaction(title: String, amount: Int, category: String) {
        transactions.add(Transaction(title, amount, category))
    }

    fun getBalance(): Int {
        return transactions.sumOf { it.amount }
    }
    fun getRemain(): Int {
        return dailybudget.value?.minus(getBalance()) ?: 0
    }

    fun getHabitPoints(): Double{
        val totalHabits = habits.size
        if (totalHabits == 0) return 0.0

        val completedHabits = habits.count { it.isDone }
        return (completedHabits.toDouble() / totalHabits.toDouble()) * 100
    }
    fun getSpendScore(): Double {
        val budget= dailybudget.value ?:
        return 0.0
        if (budget <= 0) return 0.0

        val totalSpent = transactions.sumOf { it.amount }
        val ratio = totalSpent.toDouble() / budget
        return when{
            ratio > 0 && ratio <=0.5 ->{ 100.0}
            ratio > 0.5 && ratio <= 0.8 -> {100-(((ratio - 0.5) / 0.3) * 20)}
            ratio > 0.8 && ratio <= 1.0 -> {80 - (((ratio - 0.8) / 0.2) * 30)}
            ratio > 1.0 -> {0.0}
            else -> {100.0}
        }
    }

    fun getDisciplineScore(): Double{
        val habitScore = getHabitPoints()
        val spendScore = getSpendScore()
        return(0.6*habitScore) +( 0.4*spendScore)
    }

    fun deleteHabit(habit: Habit) {
        habits.remove(habit)
    }
    fun deleteTransaction(transaction: Transaction) {
        transactions.remove(transaction)
    }
    var dailybudget = mutableStateOf<Int?>(null)
    fun setDailyBudget(amount: Int) {
        dailybudget.value = amount
    }
    fun clearDailyBudget() {
         dailybudget.value = null
    }
}
