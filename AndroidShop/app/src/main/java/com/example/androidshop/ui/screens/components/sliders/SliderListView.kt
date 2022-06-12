package com.example.androidshop.ui.screens.components.sliders

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidshop.ui.screens.components.loading.LoadingLazyRow
import com.example.androidshop.viewmoels.site.SliderViewModel

@Composable
fun SliderListView(sliderViewmodel: SliderViewModel = hiltViewModel()) {
    val dataList by remember { mutableStateOf(sliderViewmodel.dataList) }
    val isLoading by remember { mutableStateOf(sliderViewmodel.isLoading) }
    if (isLoading.value) {
        LoadingLazyRow(Modifier.width(300.dp).height(200.dp), count = 3)
    } else {

        LazyRow {
            items(dataList.value.size) { index ->
                SliderItemView(dataList.value[index])
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}