package com.example.androidshop.viewmoels.invoices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.invoices.PaymentTransction
import com.example.androidshop.repositories.invoices.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {

    fun gotoPayment(
        data: PaymentTransction,
        onResponse: (response: ServiceResponse<String>) -> Unit
    ) {
        viewModelScope.launch {
            // TODO: FIXEME TOKEN
            val response = repository.gotoPayment(data, "")
            onResponse(response)
        }
    }

}