package com.example.androidshop.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.androidshop.db.viewmodels.BasketEntityViewModel
import com.example.androidshop.ui.screens.components.BasketItemsView

@Composable
fun BasketListScreen(
    navController: NavHostController,
    basketViewModel: BasketEntityViewModel
) {
    val dataList by remember { mutableStateOf(basketViewModel.dataList) }
    var totalPrice: Long = 0
    Column(Modifier.padding(8.dp, 0.dp)) {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    modifier = Modifier.padding(
                        0.dp, if (
                            dataList.value.isNotEmpty()) 8.dp else 9.dp, 0.dp, 0.dp
                    ),
                    text = "Shoping Cart",
                    fontWeight = FontWeight.Bold, fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                if (dataList.value.isNotEmpty()) {
                    Text(
                        text = "${dataList.value.size} Items",
                        fontSize = 16.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(15.dp))
        if (dataList.value.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Your shoping cart is empty!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(40.dp))
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "",
                    tint = Color.Gray,
                    modifier = Modifier.size(250.dp)
                )
            }
        }
        LazyColumn {
            items(dataList.value.size) { index ->
                totalPrice += (dataList.value[index].quantity) * (dataList.value[index].price)
                BasketItemsView(dataList.value[index], basketViewModel, navController)
                Spacer(modifier = Modifier.height(10.dp))
            }
            if (dataList.value.isNotEmpty()) {
                item {
                    Row {
                        Text(text = "Total:", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                        Text(
                            text = " $totalPrice",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(Modifier.padding(8.dp)) {
                        Button(
                            onClick = {
                                navController.navigate("proceedToPayment")
                            },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            Text(
                                text = "Prossed to Payment",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
