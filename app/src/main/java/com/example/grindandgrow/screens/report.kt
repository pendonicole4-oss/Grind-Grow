package com.example.grindandgrow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.grindandgrow.models.MainViewModel
import java.time.YearMonth
import com.example.grindandgrow.data.AuthViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ReportScreen(nav: NavController, vm: MainViewModel) {
    val score = vm.getDisciplineScore()
    val currentdate = SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date())
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        vm.loadScores(context)
        vm.saveTodayScore(context)
    }


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
                    selected = true,
                    onClick = {nav.navigate("report") },
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

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                Text("DISCIPLINE CALENDER", fontSize = 24.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
            }
            var currentMonth by remember {
                mutableStateOf(YearMonth.now())
            }

            val firstDayOfMonth = currentMonth.atDay(1)
            val daysInMonth = currentMonth.lengthOfMonth()

            // Monday = 1 ... Sunday = 7
            val startOffset = firstDayOfMonth.dayOfWeek.value - 1

            val daysOfWeek = listOf(
                "Mon", "Tue", "Wed",
                "Thu", "Fri", "Sat", "Sun"
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                // 🔥 MONTH HEADER

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = {
                            currentMonth = currentMonth.minusMonths(1)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Previous Month"
                        )
                    }

                    Text(
                        text = "${currentMonth.month} ${currentMonth.year}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(
                        onClick = {
                            currentMonth = currentMonth.plusMonths(1)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Next Month"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // 🔥 DAYS OF WEEK HEADER

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    daysOfWeek.forEach { day ->

                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = day,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // 🔥 REAL CALENDAR GRID

                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // EMPTY SPACES BEFORE MONTH STARTS
                    items(startOffset) {

                        Box(
                            modifier = Modifier
                                .aspectRatio(1f)
                        )
                    }

                    // ACTUAL DAYS
                    items(daysInMonth) { index ->

                        val day = index + 1
                        val dateKey = currentMonth.atDay(day).toString()
                        val score = vm.dailyScores[dateKey]

                        val backgroundColor =
                            when {
                                score == null -> Color(0xFFF5F5F5)
                                score >= 85 -> Color(0xFFA5D6A7)
                                score >= 70 -> Color(0xFFFFD1A6)
                                else -> Color(0xFFF7B2AD)
                            }

                        Card(
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .aspectRatio(1f)
                                .border(
                                    1.dp,
                                    Color.LightGray,
                                    RoundedCornerShape(12.dp)
                                )
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(backgroundColor)
                                    .padding(4.dp),
                                contentAlignment = Alignment.Center
                            ) {

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    Text(
                                        text = "$day",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = score?.toString() ?: "-",
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }

                    }
                }
            }
            val context = LocalContext.current
            val myauth = AuthViewModel(nav, context)
            //  Logout Button
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedButton(
                    onClick = { myauth.logout() },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFF0),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Logout")
                }
            }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun ReportScreenPreview(){
    ReportScreen(
        nav = rememberNavController(),
        vm = MainViewModel()
    )
}
