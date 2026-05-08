package com.example.grindandgrow.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.grindandgrow.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grindandgrow.navigation.ROUTE_LOGIN
import com.example.grindandgrow.navigation.ROUTE_REGISTER
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun firstscreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF2E6))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "GRIND & GROW",
            color = Color(0xFF483C32),
            fontSize = 24.sp,
            fontFamily = FontFamily.SansSerif
        )
        Text(
            text = "Where consistency meets discipline",
            color = Color(0xFF483C32),
            fontSize = 18.sp,
            fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                onClick = { navController.navigate(ROUTE_LOGIN) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFAF2E6),
                    contentColor = Color.Black
                )
            ) {
                Text("Login")
            }
            OutlinedButton(
                onClick = { navController.navigate(ROUTE_REGISTER) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFAF2E6),
                    contentColor = Color.Black
                )
            ) {
                Text("Register")
            }
        }


    }


}
@Preview(showBackground = true)
@Composable
fun firstscreenPreview(){
    firstscreen(rememberNavController())
}