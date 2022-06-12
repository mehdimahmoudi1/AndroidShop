package com.example.androidshop.ui.screens.components.sliders

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidshop.models.site.Slider
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SliderItemView(slider: Slider) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .width(300.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(10.dp),
                clip = true
            ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box {
            GlideImage(
                imageModel = slider.image!!,
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
                    .padding(8.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Text(text = slider.title!!, color = Color.White, fontSize = 24.sp)
                    Text(text = slider.subTitle!!, color = Color.LightGray, fontSize = 16.sp)
                }
            }
        }
    }
}