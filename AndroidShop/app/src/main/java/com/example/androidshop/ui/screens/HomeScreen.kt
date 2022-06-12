package com.example.androidshop.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.androidshop.ui.screens.components.LoadingLazyCulomn
import com.example.androidshop.ui.screens.components.loading.LoadingLazyRow
import com.example.androidshop.ui.screens.components.products.ProductCategoryListView
import com.example.androidshop.ui.screens.components.products.ProductFiterView
import com.example.androidshop.ui.screens.components.products.ProductItemView
import com.example.androidshop.ui.screens.components.sliders.SliderListView
import com.example.androidshop.viewmoels.products.ProductViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: ProductViewModel = hiltViewModel()) {
    var prodctList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    LazyColumn(
        modifier = Modifier
            .padding(8.dp,0.dp).fillMaxSize()
    ) {
        item {
            SliderListView()
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            ProductCategoryListView(navController = navController)
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            ProductFiterView(viewModel,prodctList)
            Spacer(modifier = Modifier.height(10.dp))
        }
        if (isLoading.value){
            item{
            LoadingLazyRow(modifier = Modifier
                .fillMaxSize()
                .height(200.dp), count = 2)
            }
        }else{
            items(prodctList.value.size) { index ->
                if (isLoading.value){
                LoadingLazyCulomn(modifier = Modifier.fillMaxSize(), count =4 )
                }else{
                ProductItemView(prodctList.value[index],navController)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}