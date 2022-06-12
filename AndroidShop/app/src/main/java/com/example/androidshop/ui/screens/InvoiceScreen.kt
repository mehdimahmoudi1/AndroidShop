package com.example.androidshop.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidshop.ui.screens.components.AdvancedButton
import com.example.androidshop.ui.screens.components.LoadingLazyCulomn
import com.example.androidshop.ui.theme.BluePeterRiver
import com.example.androidshop.ui.theme.GreenSea
import com.example.androidshop.viewmoels.invoices.InvoiceItemViewModel
import com.example.androidshop.viewmoels.invoices.InvoiceViewModel

@Composable
fun InvoiceScreen(
    navController: NavController,
    invoiceId: Long,
    viewModel: InvoiceItemViewModel = hiltViewModel()
) {
    val invoice by remember { mutableStateOf(viewModel.data) }
    val isLoading by remember { mutableStateOf(viewModel.isLoading) }

    if (isLoading.value || invoice.value == null) {
        LoadingLazyCulomn(
            modifier = Modifier
                .fillMaxSize()
        )
    } else {
        Column(Modifier.padding(8.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.padding(5.dp),
                    shape = RoundedCornerShape(50.dp),
                    backgroundColor = if (invoice.value!!.status == "NotPayed") Red else
                        GreenSea
                ) {
                    Icon(
                        imageVector = if (invoice.value!!.status == "NotPayed") Icons.Filled.Close
                        else Icons.Filled.Check,
                        contentDescription = "",
                        Modifier
                            .size(200.dp)
                            .padding(30.dp),
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(text = "Status : ${invoice.value!!.status!!}", fontSize = 21.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Add Date : ${invoice.value!!.addData!!}", fontSize = 21.sp)
            Spacer(modifier = Modifier.height(5.dp))
            if (!invoice.value!!.paymentData.isNullOrEmpty()) {
                Text(text = "Payment Date : ${invoice.value!!.paymentData!!}", fontSize = 21.sp)
                Spacer(modifier = Modifier.height(15.dp))
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(invoice.value!!.items!!.size) { index ->
                    AdvancedButton(
                        invoice.value!!.items!![index].product!!.title!!,
                        invoice.value!!.items!![index].quantity!!.toString(),
                        Icons.Filled.List,
                        BluePeterRiver
                    ) {
                    }
                }
            }
        }
    }
}