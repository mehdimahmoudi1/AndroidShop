package com.example.androidshop.models.invoices

import com.example.androidshop.db.models.BasketEntity
import com.example.androidshop.models.products.Product

data class InvoiceItems(
    var id: Long? = null,
    var product: Product?,
    var quantity: Int?,
    var unitPrice: Long? = 0,
){
    companion object{
        fun convertFromBasket(basketEntity: BasketEntity):InvoiceItems{
            return InvoiceItems(
                product = Product(basketEntity.productId),
                quantity = basketEntity.quantity
            )
        }
    }
}
