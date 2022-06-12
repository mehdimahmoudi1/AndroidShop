package com.example.androidshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.androidshop.db.viewmodels.UserEntityViewModel
import com.example.androidshop.ui.theme.AndroidShopTheme
import com.example.androidshop.utils.ThisApp
import dagger.hilt.android.AndroidEntryPoint
import com.example.androidshop.ui.screens.SplashScreen

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isLoad by remember { mutableStateOf(false) }
            val userEntityViewModel =
                ViewModelProvider(this).get(UserEntityViewModel::class.java)
            userEntityViewModel.getCurrentUser().observe(this) {
                isLoad = true
                userEntityViewModel.currentUser.value = it
            }
            AndroidShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (userEntityViewModel.currentUser.value != null) {
                        ThisApp.token = userEntityViewModel.currentUser.value!!.token!!
                    }
                    if (isLoad)
                        SplashScreen(this, userEntityViewModel)
                }
            }
        }
    }
}