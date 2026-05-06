package com.example.grindandgrow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

import com.example.grindandgrow.navigation.ROUTE_FIRSTSCREEN
import com.example.grindandgrow.navigation.ROUTE_SPLASH

@Composable
fun splashscreen(navController: NavHostController) {
    LaunchedEffect(true) {
        delay(2000)
        navController.navigate(ROUTE_FIRSTSCREEN) {
            popUpTo(ROUTE_SPLASH) { inclusive = true }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
        .background(Color(0xFFFFFFF0)),
        contentAlignment = Alignment.Center

    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("GRIND & GROW",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif,
                color = Color(0xFF483C32)
            )
            Spacer(modifier = Modifier.height(10.dp))

        }
    }

}
@Preview(showBackground = true)
@Composable
fun splashscreenPreview(){
    splashscreen(rememberNavController())
}