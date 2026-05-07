package com.example.grindandgrow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.grindandgrow.models.MainViewModel
import com.example.grindandgrow.navigation.ROUTE_HABIT


@OptIn(ExperimentalMaterial3Api::class)


@Composable
fun HomeScreen(nav: NavController, vm: MainViewModel) {
    val score = vm.getDisciplineScore()

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
            NavigationBar(containerColor = Color(0xFF8D7B68)) {
                NavigationBarItem(
                    selected = true,
                    onClick = { nav.navigate("home") },
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
                    selected = false,
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
    )

    { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .background(Color(0xFFFFFFF0))
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        )
        {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {Text("Consistency builds discipline, not perfection",  color = Color.Black) }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Discipline Score", fontSize = 24.sp, color = Color.Black)
                Text("${score.toInt()}/100", fontSize = 32.sp)
                Spacer(modifier = Modifier.height(16.dp))

                LinearProgressIndicator(
                    progress = { (score / 100).toFloat() },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text("Habits Done: ${vm.habits.count { it.isDone }}/${vm.habits.size}")
                Spacer(modifier = Modifier.height(16.dp))
                Text("Money Spent: Ksh ${vm.getBalance()}")
                Spacer(modifier = Modifier.height(16.dp))
                Text("Remaining Balance: Ksh ${vm.getRemain()}")


            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(
        nav = rememberNavController(),
        vm = MainViewModel()
    )
}