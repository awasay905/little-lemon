package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavHostController) {
    var context = LocalContext.current;
    var sharedPreferences = context.getSharedPreferences("LittleLemonApp", Context.MODE_PRIVATE)

    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier
        .fillMaxWidth().fillMaxHeight()
        .padding(20.dp)) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .padding(30.dp)
                .height(50.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Profile Information:",
        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Left, fontWeight = FontWeight.Bold,
        fontSize = 30.sp, fontFamily = FontFamily.SansSerif)

        //Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(value = "${sharedPreferences.getString("firstName","")}" , onValueChange = {},
        label = {Text("First Name")}, readOnly = true, modifier = Modifier.fillMaxWidth())

        //Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(value = "${sharedPreferences.getString("lastName","")}", onValueChange = {},
            label = {Text("Last Name")},  readOnly = true, modifier = Modifier.fillMaxWidth())

        //Spacer(modifier = Modifier.height(1.dp))
        OutlinedTextField(value = "${sharedPreferences.getString("emailAddress","")}", onValueChange = {},
            label = {Text("Email Address")},  readOnly = true,modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(180.dp))

        Button(onClick = {
            with(sharedPreferences.edit()) {
                putString("firstName", "")
                putString("lastName", "")
                putString("emailAddress", "")
                putBoolean("onboarding", true)
            }.apply()
            navController.navigate(OnBoarding.route)
        },
             modifier = Modifier.fillMaxWidth()) {
            Text(text = "Logout", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 20.sp)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfilePreview() {
}