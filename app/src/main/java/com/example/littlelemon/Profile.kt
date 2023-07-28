package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Profile(navController: NavHostController) {
    var context = LocalContext.current;
    var sharedPreferences = context.getSharedPreferences("LittleLemonApp", Context.MODE_PRIVATE)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .padding(30.dp)
                .height(50.dp)
        )

        Text("Profile Information:")
        Text(text = "First Name: ${sharedPreferences.getString("firstName","")}")
        Text(text = "Last Name: ${sharedPreferences.getString("lastName","")}")
        Text(text = "Email Address: ${sharedPreferences.getString("emailAddress","")}")


        Button(onClick = {
            with(sharedPreferences.edit()) {
                putString("firstName", "")
                putString("lastName", "")
                putString("emailAddress", "")
                putBoolean("onboarding", true)
            }.apply()
            navController.navigate(OnBoarding.route)
        }) {
            Text(text = "Logout")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfilePreview() {
}