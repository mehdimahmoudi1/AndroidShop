package com.example.androidshop.models.invoices

import com.example.androidshop.models.cutomers.UserVM

data class Invoice(
    var id: Long?,
    var addData: String?,
    var paymentData: String? ,
    var status: String? ,
    var transactions: List<Transction>?,
    var items: List<InvoiceItems>?,
    var user: UserVM?
)
