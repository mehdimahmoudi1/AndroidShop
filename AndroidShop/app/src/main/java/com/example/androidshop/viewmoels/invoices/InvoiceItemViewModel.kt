package com.example.androidshop.viewmoels.invoices

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidshop.models.ServiceResponse
import com.example.androidshop.models.invoices.Invoice
import com.example.androidshop.repositories.invoices.InvoiceRepository
import com.example.androidshop.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceItemViewModel @Inject constructor(
    private val repository: InvoiceRepository
) : ViewModel() {

    var token: String = ThisApp.token
    var invoiceId: Long = ThisApp.invoiceId

    var data = mutableStateOf<Invoice?>(null)
    var isLoading = mutableStateOf(true)

    init {
        getInvoiceById(invoiceId) { response ->
            isLoading.value = false
            if (response.status == "OK") {
                data.value = response.data!![0]
            }
        }
    }

    private fun getInvoiceById(
        id: Long,
        onResponse: (response: ServiceResponse<Invoice>) -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.getInvoiceById(id, token)
            onResponse(response)
        }
    }

}