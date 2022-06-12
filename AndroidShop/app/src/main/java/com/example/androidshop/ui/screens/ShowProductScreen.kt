package com.example.androidshop.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidshop.db.models.BasketEntity
import com.example.androidshop.db.viewmodels.BasketEntityViewModel
import com.example.androidshop.ui.screens.components.loading.LoadingLazyRow
import com.example.androidshop.viewmoels.products.ProductViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ShowProductScreen(
    productId: Long,
    navController: NavController,
    basketViewModel : BasketEntityViewModel,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val data by remember { mutableStateOf(viewModel.data) }
    var isLoading by remember { mutableStateOf(true) }
    var selectedSize by remember { mutableStateOf(0) }
    var selectedColor by remember { mutableStateOf(0) }
    viewModel.getProductById(productId) { response ->
        isLoading = false
        if (response.status == "OK") {
            if (response.data!!.isNotEmpty()) {
                viewModel.data.value = response.data!![0]
            } else {
                Toast.makeText(context, "Error in loading data!", Toast.LENGTH_LONG).show()
                navController.popBackStack()
            }
        }
    }

    if (isLoading) {
        LoadingLazyRow(modifier = Modifier.fillMaxSize(), count = 1)
    } else {
        Card(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Box {
                GlideImage(
                    imageModel = data.value!!.image!!,
                    loading = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    },
                    // shows an error text if fail to load an image.
                    failure = {
                        Text(text = "image request failed.")
                    })
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                )
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column {
                        Text(text = data.value!!.title!!, color = Color.White, fontSize = 24.sp)
                        Text(
                            text = "${data.value!!.price!!}T",
                            color = Color.White,
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            text = "Sizes",
                            fontSize = 24.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        LazyRow {
                            items(data.value!!.sizes!!.size) { index ->
                                TextButton(
                                    onClick = {
                                        selectedSize = index
                                    },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = if (selectedSize == index) Color.White else Color.Transparent)
                                ) {
                                    Text(
                                        text = data.value!!.sizes!![index].title!!,
                                        fontSize = 20.sp, fontWeight = FontWeight.Bold,
                                        color = if (selectedSize == index) Color.Black else Color.White
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            text = "Colors",
                            fontSize = 24.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        LazyRow {
                            items(data.value!!.colors!!.size) { index ->
                                TextButton(
                                    onClick = { selectedColor = index },
                                    shape = RoundedCornerShape(50.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor =
                                        Color(
                                            android.graphics.Color.parseColor(
                                                "#${data.value!!.colors!![index].hexValue}"
                                            )
                                        )
                                    ), modifier = Modifier.size(40.dp),
                                    border = BorderStroke(1.dp, color = Color.White)
                                ) {
                                    if (selectedColor == index) {
                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "",
                                            tint = if (data.value!!.colors!![index].hexValue == "FFFFFF") Color.Black else Color.White
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.size(5.dp))
                            }
                        }

                        Spacer(modifier = Modifier.size(20.dp))
                        Button(
                            onClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    val basket = BasketEntity(
                                        productId = productId,
                                        quantity = 1,
                                        colerId = data.value!!.colors!![selectedColor].id!!,
                                        sizeId = data.value!!.sizes!![selectedSize].id!!,
                                        image = data.value!!.image!!,
                                        price = data.value!!.price!!,
                                        title = data.value!!.title!!,
                                         colorHex =  data.value!!.colors!![selectedColor].hexValue!!,
                                         size =  data.value!!.sizes!![selectedSize].title!!
                                    )
                                basketViewModel.addToBasket(basket)
                                }
                                Toast.makeText(context,"Product added to your basket",Toast.LENGTH_LONG).show()
                                navController.popBackStack()
                            },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                        ) {
                            Text(
                                text = "Buy Now",
                                color = Color.Red,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.size(20.dp))

                    }
                }
            }
        }
    }
}