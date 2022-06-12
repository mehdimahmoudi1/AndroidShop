package com.example.androidshop.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidshop.MainActivity
import com.example.androidshop.SplashActivity
import com.example.androidshop.db.viewmodels.UserEntityViewModel
import com.example.androidshop.viewmoels.costomers.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun SplashScreen(
    splashActivity: SplashActivity,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel()
) {
    var isLoading by remember { mutableStateOf(false)}

    if(!isLoading) {
        userViewModel.getUserInfo { response ->
            if (response.status != "OK") {
                if (response.message!!.lowercase(Locale.ROOT).startsWith("failed to connect to")) {
                    Toast.makeText(
                        splashActivity,
                        "Unable to connect to the server",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    return@getUserInfo
                } else if (response.message!!.lowercase(Locale.ROOT).startsWith("http 417")) {
                    CoroutineScope(Dispatchers.IO).launch {
                        userEntityViewModel.deleteAll()
                    }
                }
            }
            splashActivity.startActivity(Intent(splashActivity, MainActivity::class.java))
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "",
            Modifier.size(250.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        CircularProgressIndicator()
    }
}