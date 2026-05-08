package com.example.grindandgrow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.grindandgrow.models.MainViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun BudgetScreen( nav: NavController, vm: MainViewModel) {
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var budgetinput by remember { mutableStateOf("") }
    val currentdate = SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date())




    Scaffold(
        containerColor = Color(0xFFFFFFF0),
        topBar = {
            TopAppBar(
                title = { Text("Grind & Grow") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF8D7B68),
                    titleContentColor = Color.Black
                ),
                actions = {
                    Text(
                        text = currentdate,
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF8D7B68)) {
                NavigationBarItem(
                    selected = false,
                    onClick = {nav.navigate("home") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home Icon") },
                    label = { Text("HOME") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { nav.navigate("habit") },
                    icon = { Icon(Icons.Default.AddTask, contentDescription = "habit Icon") },
                    label = { Text("HABIT") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { nav.navigate("budget") },
                    icon = { Icon(Icons.Default.Money, contentDescription = "Money Icon") },
                    label = { Text("BUDGET") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { nav.navigate("report") },
                    icon = { Icon(Icons.Default.AutoGraph, contentDescription = "Graph Icon") },
                    label = { Text("REPORT") }
                )
            }
        }
    ) { innerPadding ->


        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .background(Color(0xFFFFFFF0)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = budgetinput,
                    onValueChange = { budgetinput = it },
                    label = { Text("Daily Budget") },
                    modifier = Modifier.weight(1f)

                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        val budget = budgetinput.toIntOrNull()
                        if (budget != null) {
                            vm.setDailyBudget(budget)
                            budgetinput = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD6CEDD),
                        contentColor = Color.Black
                    )
                ) {Text("Set") }

            }
            Spacer(modifier = Modifier.height(10.dp))
            vm.dailybudget.value?.let { dailyBudget ->
                Row  (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFD6CEDD))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,



                ) {
                    Text("Daily Budget: Ksh $dailyBudget", fontSize = 18.sp, color = Color.Black)

                    IconButton(onClick = {
                        vm.clearDailyBudget()
                    }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Reset Budget")
                    }





                }

            }
            Spacer(modifier = Modifier.height(10.dp))


            Text("SPENT: Ksh ${vm.getBalance()}", fontSize = 20.sp, color = Color.Black )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Spent On ?") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount") },
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                val amt = amount.toIntOrNull()
                if (title.isNotEmpty() && amt != null) {
                    vm.addTransaction(title, amt, "General")
                    title = ""
                    amount = ""
                }
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD6CEDD),
                    contentColor = Color.Black
                )) {
                Text("Add Transaction")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text("Today's Transactions", fontSize = 22.sp, color = Color.Black, textAlign = TextAlign.Left, textDecoration = TextDecoration.Underline)
            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn() {
                itemsIndexed(vm.transactions) {index, t ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${t.title}    -    Ksh ${t.amount}")
                        IconButton(
                            onClick = {
                                vm.transactions.remove(t)
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Delete Transaction")
                        }
                    }



                }
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun BudgetScreenPreview(){
    BudgetScreen(
        nav = rememberNavController(),
        vm = MainViewModel()
    )
}
