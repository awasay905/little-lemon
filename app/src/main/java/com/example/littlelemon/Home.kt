package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController, databas: AppDatabas){
    val menuItems by databas.menuDao().getAll().observeAsState(emptyList())
    var searchPhrase by remember{
        mutableStateOf("")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            Modifier
                .padding(20.dp)
                .height(70.dp)){
            Spacer(modifier = Modifier.weight(1f))
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = null, modifier= Modifier
                .weight(2f)
                .fillMaxSize()
                .height(50.dp))
            Image(painter = painterResource(id = R.drawable.profile), contentDescription = null,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Profile.route)
                    }
                    .weight(1f), contentScale = ContentScale.Fit)
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(color = Color(0xFF495E57))){
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(20.dp)) {
                Text(text = "Little Lemon", fontSize = 40.sp, color = Color(0xFFF4CE14), textAlign = TextAlign.Left)
                Text(text = "Chicago", fontSize = 30.sp, color = Color.White, textAlign = TextAlign.Left)
                Row(modifier=Modifier.height(100.dp)) {
                    Text(text = stringResource(R.string.description), modifier = Modifier.weight(7f), color=Color.White)
                    Image(painter = painterResource(id =  R.drawable.hero_image), contentDescription = null,
                    contentScale = ContentScale.Crop, modifier = Modifier
                            .weight(3f)
                            .clip(
                                RoundedCornerShape(3.dp)
                            ))
                }

                OutlinedTextField(value = searchPhrase, onValueChange = { searchPhrase = it }, label = {Text("Search")}, placeholder = { Text(
                    text = "Enter Search Phrase Here"
                )},leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
                modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black, containerColor = Color.White, unfocusedLeadingIconColor = Color.Black, focusedLeadingIconColor = Color.Gray
                ))

            }
        }
        Text(text = "    Order For Delivery", fontSize = 20.sp, textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth())

        val categories = setOf("starters", "mains", "desserts", "drinks")
        var currentlySelected by remember {
            mutableStateOf("")
        }
        Row (horizontalArrangement = Arrangement.SpaceBetween){
            categories.forEach {
                Button(onClick = {
                    if (currentlySelected.equals(it, ignoreCase = true)) currentlySelected = ""
                    else currentlySelected = it
                }) {
                    Text(text = it)
                }
            }
        }

        var categorizedList = if (currentlySelected.isEmpty()) menuItems else menuItems.filter {
            it.category.contains(currentlySelected, ignoreCase = true)
        }
        MenuItems(if (searchPhrase.isEmpty()) categorizedList else categorizedList.filter {
            it.title.contains(searchPhrase, ignoreCase = true)
        })
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomePreview(){}

@Composable
fun MenuItems(menuItems: List<MenuItem>) {
    Column (
        Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())){
        menuItems.forEach { MenuItemComposable(menuItem = it);
        }
    }
}
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemComposable(menuItem: MenuItem){
    Row (modifier = Modifier.height(150.dp)){
        Column (modifier=Modifier.weight(3f)){
            Text(text = menuItem.title)
            Text(text = menuItem.description)
            Text(text = "$${menuItem.price}")
        }
        Column (modifier=Modifier.weight(1f), verticalArrangement = Arrangement.Center){
            GlideImage(model = menuItem.image, contentDescription = menuItem.title)
        }
    }
}