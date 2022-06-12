package com.example.androidshop.models.invoices

import com.example.androidshop.models.cutomers.UserVM

data class PaymentTransction(
var items : List<InvoiceItems>,
var user : UserVM
)
