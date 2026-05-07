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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.grindandgrow.models.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitScreen(nav: NavController, vm: MainViewModel) {
    var text by remember { mutableStateOf("") }


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
                    selected = false,
                    onClick = {nav.navigate("home") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home Icon") },
                    label = { Text("HOME") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { nav.navigate("habit") },
                    icon = { Icon(Icons.Default.AddTask, contentDescription = "habit Icon") },
                    label = { Text("HABIT") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {nav.navigate("budget") },
                    icon = { Icon(Icons.Default.Money, contentDescription = "Money Icon") },
                    label = { Text("BUDGET") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {nav.navigate("report") },
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
                .fillMaxSize()
                .background(Color(0xFFFFFFF0)),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text("HABITS", fontSize = 24.sp, color = Color(0xFF483C32))
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("New Habit") }
            )
            Spacer(modifier = Modifier.height(10.dp))

            val categories = listOf("Productivity", "Health", "Mental Health", "Self-Care","Hobbies","Study","Other")
            var expanded by remember { mutableStateOf(false) }
            var selectedCategory by remember { mutableStateOf("") }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = selectedCategory,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Category") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()

                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category) },
                            onClick = {
                                selectedCategory = category
                                expanded = false
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }


           OutlinedButton(
                onClick = {
                    if (text.isNotEmpty()) {
                        vm.addHabit(text,  category = selectedCategory, isDone = false)
                        text = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFFFF0),

                    contentColor = Color.Black
                )
            ) {
                Text("Add Habit")
            }
            Spacer(modifier = Modifier.height(15.dp))

            Text("Today's Habits", fontSize = 22.sp, color = Color.Black, textAlign = TextAlign.Left, textDecoration = TextDecoration.Underline)
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                itemsIndexed(vm.habits) { index, habit ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(habit.name)

                        Text(text = habit.category, fontSize = 12.sp)

                        Checkbox(
                            checked = habit.isDone,
                            onCheckedChange = {
                                vm.toogleHabit(index)
                            }
                        )
                        IconButton(
                            onClick = {
                                vm.deleteHabit(habit)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Delete Habit"
                            )
                        }
                    }
                }
            }
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun HabitScreenPreview() {
    HabitScreen(
        rememberNavController(),
        vm = MainViewModel()
    )
}
