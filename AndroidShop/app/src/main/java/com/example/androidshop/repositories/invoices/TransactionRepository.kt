package com.example.androidshop.repositories.invoices

import com.example.androidshop.api.invoices.InvoiceApi
import com.example.androidshop.api.invoices.TransactionApi
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.invoices.Invoice
import com.example.androidshop.models.invoices.PaymentTransction
import com.example.androidshop.repositories.base.BaseRepositry
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class TransactionRepository @Inject constructor(private val api: TransactionApi){

    suspend fun gotoPayment(data: PaymentTransction, token: String): ServiceResponse<String> {
        return try {
            api.gotoPayment(data)
        } catch (e: Exception) {
            ServiceResponse(status = "Exception", message = e.message)
        }
    }

}