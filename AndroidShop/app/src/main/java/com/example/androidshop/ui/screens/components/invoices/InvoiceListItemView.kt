package com.example.androidshop.ui.screens.components.invoices

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.navigation.NavController
import com.example.androidshop.models.invoices.Invoice
import com.example.androidshop.ui.screens.components.AdvancedButton

@Composable
fun InvoiceListItemView(invoice: Invoice, navController: NavController) {

    AdvancedButton(
        invoice.addData!!, invoice.status!!,
        if (invoice.status == "NotPayed") Icons.Filled.Close else
            Icons.Filled.Check,
        if (invoice.status == "NotPayed") Red else
            Color.Green
    ) {
        navController.navigate("invoice/${invoice.id}")
    }
}