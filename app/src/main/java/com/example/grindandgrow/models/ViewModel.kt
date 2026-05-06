package com.example.grindandgrow.models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel(){
    var habits = mutableStateListOf<Habit>()
    var transactions = mutableStateListOf<Transaction>()

    fun addHabit(name: String, points: Int){
        habits.add(Habit(name, points))
    }
    fun toogleHabit(index: Int){
        val habit = habits[index]
        habits[index] = habit.copy(isDone = !habit.isDone)
    }

    fun addTransaction(title: String, amount: Int, category: String){
        transactions.add(Transaction(title, amount, category))
    }
    fun getBalance(): Int{
        return transactions.sumOf { it.amount }
    }
    fun getHabitPoints(): Int{
        return habits.filter { it.isDone }.sumOf { it.points }
    }
    fun getDisciplineScore(): Int{
        val habitScore=getHabitPoints()
        val balanceBonus=if
                (getBalance()>0) 20 else -10
        return (habitScore+balanceBonus).coerceIn(0,100)
    }
}