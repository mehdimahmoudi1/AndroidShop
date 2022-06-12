package com.example.androidshop.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.androidshop.ui.screens.components.LoadingLazyCulomn
import com.example.androidshop.ui.screens.components.products.ProductItemView
import com.example.androidshop.viewmoels.products.ProductByCategoryViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsScreen(
    categoryId: Long,
    title: String?,
    navController: NavHostController,
    viewModel: ProductByCategoryViewModel = hiltViewModel()
) {
    val dataList by remember { mutableStateOf(viewModel.dataList) }
    val isLoading by remember { mutableStateOf(viewModel.isLoading) }

        LazyColumn(modifier = Modifier
            .padding(8.dp)
            .fillMaxHeight()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp, 20.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Text(text = title!!, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
            items(dataList.value.size) { index ->
                viewModel.onScrollPositionChange(index)
                if ((index + 1) >= (viewModel.pageIdex.value * viewModel.pageSize) && !viewModel.isLoading.value) {
                    viewModel.nextPage()
                }
                ProductItemView(dataList.value[index], navController = navController)
                Spacer(modifier = Modifier.height(10.dp))
            }
            if (isLoading.value) {
                item {
                    LoadingLazyCulomn(modifier = Modifier.fillMaxSize(), count =4 )
                }

            }
        }
    }