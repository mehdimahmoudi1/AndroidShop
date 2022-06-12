package com.example.androidshop.api.invoices

import android.view.SurfaceControl
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.invoices.Invoice
import com.example.androidshop.models.invoices.PaymentTransction
import com.example.androidshop.models.invoices.Transction
import com.example.androidshop.models.site.Blog
import retrofit2.http.*

interface TransactionApi {

    @POST("/api/trx/gotoPayment")
    suspend fun gotoPayment(
        @Body data: PaymentTransction
    ): ServiceResponse<String>
}