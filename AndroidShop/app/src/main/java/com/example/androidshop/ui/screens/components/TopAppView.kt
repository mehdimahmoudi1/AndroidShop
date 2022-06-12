package com.example.androidshop.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.androidshop.db.viewmodels.BasketEntityViewModel
import com.example.androidshop.db.viewmodels.UserEntityViewModel

@Composable
fun TopAppVeiw(
    navController: NavHostController,
    basketViewModel: BasketEntityViewModel,
    userEntityViewModel: UserEntityViewModel,
    showHomeButton: Boolean
) {
    TopAppBar(
        title = { Text(text = "Online Shop", fontSize = 20.sp) },
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
        actions = {
            if (showHomeButton) {
                IconButton(onClick = {
                    navController.navigate("home")
                }) {
                    Icon(imageVector = Icons.Outlined.Home, contentDescription = "")
                }
            }
            IconButton(onClick = { navController.navigate("basket") }) {
                Box(contentAlignment = Alignment.BottomEnd) {
                    if (basketViewModel.dataList.value.isNotEmpty()) {
                        Card(
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .size(14.dp)
                                .shadow(elevation = 0.dp)
                                .alpha(255F),
                            backgroundColor = Color.Red,
                        ) {
                            Text(
                                text = "${basketViewModel.dataList.value.size}",
                                fontSize = 9.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "")
                }
            }
            IconButton(onClick = {
                if (!userEntityViewModel.isLoggedIn()) {
                    navController.navigate("login")
                } else {
                    navController.navigate("dashbord")
                }
            }) {
                Icon(imageVector = Icons.Outlined.Person, contentDescription = "")
            }
        }
    )
}