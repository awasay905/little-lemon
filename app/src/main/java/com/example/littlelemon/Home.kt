package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController){
    Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
    Image(painter = painterResource(id = R.drawable.profile), contentDescription = null,
    modifier = Modifier.clickable {
        navController.navigate(Profile.route)
    })
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomePreview(){}
