package com.example.androidshop.ui.screens.components.products

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidshop.ui.screens.components.loading.LoadingLazyRow
import com.example.androidshop.viewmoels.products.ProductCategoryViewModel


@Composable
fun ProductCategoryListView(viewModel: ProductCategoryViewModel = hiltViewModel(),navController:NavController) {
    val dataList by remember { mutableStateOf(viewModel.dataList) }
    val isLoading by remember { mutableStateOf(viewModel.isLoading) }
    if (isLoading.value){
        LoadingLazyRow(Modifier.width(160.dp).height(200.dp), count = 3)
    }else{
        LazyRow {
            items(dataList.value.size) { index ->
                ProductCategoryItemView(dataList.value[index], navController)
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}