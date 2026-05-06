package com.example.grindandgrow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.grindandgrow.models.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun BudgetScreen( nav: NavController, vm: MainViewModel) {
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Grind & Grow") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFDE3C4),
                    titleContentColor = Color(0xFF483C32)
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "Menu",
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFFFDE3C4)) {
                NavigationBarItem(
                    selected = false,
                    onClick = { },
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
                    onClick = { },
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
            Text("BALANCE: Ksh ${vm.getBalance()}", fontSize = 22.sp, color = Color(0xFF483C32))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Spent On ?") }
            )
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Amount") }
            )
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
                    containerColor = Color(0xFF800020),
                    contentColor = Color.Black
                )) {
                Text("Add Transaction")
            }
            LazyColumn() {
                items(vm.transactions) { t ->
                    Text("${t.title} : Ksh ${t.amount}")
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
