package com.example.androidshop.ui.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androidshop.R
import com.example.androidshop.db.models.BasketEntity
import com.example.androidshop.db.viewmodels.BasketEntityViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasketItemsView(basketEntity: BasketEntity, viewModel: BasketEntityViewModel,navController: NavController) {
    var qauntity by remember{ mutableStateOf(basketEntity.quantity)}
    var price by remember{ mutableStateOf(basketEntity.price)}
    Row {
        Card(
            modifier = Modifier
                .height(160.dp)
                .width(140.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp),
                    clip = true
                ),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                navController.navigate("showProduct/${basketEntity.productId}")
            }
        ) {
            GlideImage(
                imageModel = basketEntity.image,
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
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = "Title: ${basketEntity.title}")
            Text(text = "Price: $price T")
            Text(text = "Size: ${basketEntity.size}")
            Card(
                shape = RoundedCornerShape(50.dp),
                backgroundColor =
                Color(
                    android.graphics.Color.parseColor(
                        "#${basketEntity.colorHex}"
                    )
                ), modifier = Modifier.size(40.dp),
                border = BorderStroke(1.dp, color = Color.Black),
                content = {}
            )
            Row {
                Spacer(modifier = Modifier.width(30.dp))

                IconButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.decrementQuantity(basketEntity)
                    }
                    qauntity--
                    price -= basketEntity.price
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_remove_circle_24),
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = qauntity.toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(2.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.incrementQuantity(basketEntity)
                    }
                    qauntity++
                    price += basketEntity.price
                }) {
                    Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "")
                }
                Spacer(modifier = Modifier.width(40.dp))
                IconButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.delete(basketEntity)
                    }

                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "",
                        tint = Color.Red
                    )
                }

            }
        }
        Spacer(modifier = Modifier.width(10.dp))

    }
}