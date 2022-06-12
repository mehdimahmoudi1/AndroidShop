package com.example.androidshop.models.products

import com.example.androidshop.models.invoices.InvoiceItems

data class Product(
    var id: Long? = null,
    var title: String? = "",
    var description: String? = "",
    var image: String? = "",
    val category: ProductCategory? = null,
    var price: Long? = 0,
    var addData: String? = "",
    var visitCunt: Int? = 0,
    var colors: List<ProductColor>? = null,
    var sizes: List<ProductSize>? = null,
    val items: List<InvoiceItems>? = null,
)
