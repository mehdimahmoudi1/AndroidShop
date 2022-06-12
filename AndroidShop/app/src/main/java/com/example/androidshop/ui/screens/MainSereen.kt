package com.example.androidshop.ui.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.androidshop.MainActivity
import com.example.androidshop.db.viewmodels.BasketEntityViewModel
import com.example.androidshop.db.viewmodels.UserEntityViewModel
import com.example.androidshop.ui.screens.components.TopAppVeiw
import com.example.androidshop.utils.ThisApp

@Composable
fun MainScreen(mainActivity: MainActivity) {
    val navController = rememberNavController()
    var fullScreen by remember { mutableStateOf(false) }
    var showHomeButton by remember { mutableStateOf(false) }
    val basketViewModel =
        ViewModelProvider(mainActivity).get(BasketEntityViewModel::class.java)

    val userEntityViewModel =
        ViewModelProvider(mainActivity).get(UserEntityViewModel::class.java)

    basketViewModel.getBasketListLive().observe(mainActivity) {
        basketViewModel.dataList.value = it
    }
    userEntityViewModel.getCurrentUser().observe(mainActivity) {
        userEntityViewModel.currentUser.value = it
    }
    Scaffold(
        topBar = {
            if (!fullScreen)
                TopAppVeiw(navController, basketViewModel, userEntityViewModel, showHomeButton)
        }
    ) {

        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                fullScreen = false
                HomeScreen(navController)
            }
            composable("basket") {
                fullScreen = true
                BasketListScreen(navController, basketViewModel)
            }
            composable("proceedToPayment") {
                fullScreen = true
                ProceedToPaymentScreen(
                    navController,
                    basketViewModel,
                    mainActivity,
                    userEntityViewModel
                )
            }
            composable(
                "showProduct/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.LongType })
            ) { backStack ->
                fullScreen = true
                backStack.arguments?.getLong("productId").let {
                    ShowProductScreen(it!!, navController, basketViewModel)
                }
            }
            composable(
                "products/{categoryId}/{title}",
                arguments = listOf(
                    navArgument("categoryId") { type = NavType.LongType },
                    navArgument("title") { type = NavType.StringType }
                )

            ) { backStack ->
                fullScreen = true
                val id = backStack.arguments?.getLong("categoryId")
                val title = backStack.arguments?.getString("title")
                ThisApp.categoryId = id!!
                ProductsScreen(id, title!!, navController)

            }

            composable("login") {
                fullScreen = true
                LogInScreen(navController, userEntityViewModel)
            }
            composable("dashbord") {
                fullScreen = true
                DashboardScreen(navController, userEntityViewModel)
            }

            composable(
                "invoice/{id}",
                deepLinks = listOf(navDeepLink { uriPattern = "app://androidshop.ir/{id}" }),
                arguments = listOf(
                    navArgument("id") { type = NavType.LongType }
                )
            ) { backStackEntry ->
                showHomeButton = true
                if (userEntityViewModel.currentUser.value != null) {
                    ThisApp.invoiceId = backStackEntry.arguments?.getLong("id")!!
                    ThisApp.token = userEntityViewModel.currentUser.value!!.token!!
                    InvoiceScreen(navController, backStackEntry.arguments?.getLong("id")!!)
                }
            }
            composable("invoices") {
                fullScreen = true
                InvoiceListScreen(navController)
            }
            composable("edit") {
                fullScreen = true
                EditProfileScreen(navController, userEntityViewModel)
            }
        }
    }
}