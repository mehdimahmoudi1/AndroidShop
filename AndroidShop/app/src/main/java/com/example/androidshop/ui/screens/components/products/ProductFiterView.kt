package com.example.androidshop.ui.screens.components.products

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidshop.models.products.Product
import com.example.androidshop.viewmoels.products.ProductViewModel

@Composable
fun ProductFiterView(
    viewModel: ProductViewModel = hiltViewModel(),
    prodctList: MutableState<List<Product>>
) {
    val filters = listOf("All", "New", "Popular")
    var selectedFilter by remember { mutableStateOf(0) }
    LazyRow {
        items(filters.size) { index ->
            TextButton(
                onClick = { selectedFilter = index
                          when(selectedFilter){
                              0->{
                                  viewModel.getProduct(0,6){}
                              }
                              1->{
                                  viewModel.getNewProduct {}
                              }
                              2->{
                                  viewModel.getPopularProduct{}
                              }
                          }},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (
                        selectedFilter == index
                    ) Color.LightGray else Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.width(90.dp)

            ) {
                Text(
                    text = filters[index],
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}