package com.example.androidshop.repositories.invoices

import com.example.androidshop.api.invoices.InvoiceApi
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.invoices.Invoice
import com.example.androidshop.repositories.base.BaseRepositry
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class InvoiceRepository @Inject constructor(private val api: InvoiceApi) : BaseRepositry() {

    suspend fun addNewInvoice(data: Invoice, token: String): ServiceResponse<Invoice> {
        return try {
            api.addInvoice(data, preperToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getInvoiceById(
        id: Long,
        token: String
    ): ServiceResponse<Invoice> {
        return try {
            api.getInvoiceById(id, preperToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }


    suspend fun getInvoiceByUserId(
        userId: Long,
        pageIndex: Int,
        pageSize: Int,
        token: String
    ): ServiceResponse<Invoice> {
        return try {
            api.getInvoiceByUserId(userId, pageIndex, pageSize, preperToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}