package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(navController: NavController) {
    var context = LocalContext.current;
    var sharedPreferences = context.getSharedPreferences("LittleLemonApp", Context.MODE_PRIVATE)
    val isDataStored = sharedPreferences.getBoolean("onboarding", true)

    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }
    var emailAddress by remember {
        mutableStateOf("")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().padding(15.dp)) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .padding(30.dp)
                .height(50.dp)
        )
        Box(modifier = Modifier.height(120.dp)) {
            Text(
                "Let's get to know you",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = Color.Green),
                textAlign = TextAlign.Center,
            )
        }

        OutlinedTextField(value = firstName, onValueChange = { firstName = it },
            label = { Text("First Name") }, placeholder = { Text("First Name") })
        OutlinedTextField(value = lastName, onValueChange = { lastName = it },
            label = { Text("Last Name") }, placeholder = { Text("Last Name") })
        OutlinedTextField(value = emailAddress, onValueChange = { emailAddress = it },
            label = { Text("Email Address") })
        Spacer(modifier = Modifier.height(200.dp))
        Button(onClick = {
            if ((firstName.isEmpty() && lastName.isEmpty() && emailAddress.isEmpty())) {
                Toast.makeText(
                    context,
                    "Registatrion Unsuccessful, PLease Enter all Data>",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                with(sharedPreferences.edit()) {
                    putString("firstName", firstName)
                    putString("lastName", lastName)
                    putString("emailAddress", emailAddress)
                    putBoolean("onboarding", false)
                }.apply()
                Toast.makeText(
                    context,
                    "Registatrion Successful",
                    Toast.LENGTH_SHORT
                ).show()
                navController.navigate(Home.route)
            }
        }) {
            Text("Register")
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingPreview() {
    Onboarding(rememberNavController())
}

