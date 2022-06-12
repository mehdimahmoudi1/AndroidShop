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
class InvoiceViewModel @Inject constructor(
    private val repository: InvoiceRepository
) : ViewModel() {
    var token: String = ThisApp.token
    var userId: Long = ThisApp.userId
    var pageSize = 3
    var pageIndex = mutableStateOf(0)
    private var scrollPosition = 0

    var dataList = mutableStateOf<List<Invoice>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getInvoiceByUserId(userId, pageIndex.value, pageSize) { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    fun getInvoiceById(
        id: Long,
        onResponse: (response: ServiceResponse<Invoice>) -> Unit
    ) {
        viewModelScope.launch {
            val response = repository.getInvoiceById(id, token)
            onResponse(response)
        }
    }

    private fun getInvoiceByUserId(
        userId: Long,
        pageIndex: Int,
        pageSize: Int, onResponse: (response: ServiceResponse<Invoice>) -> Unit
    ) {
        viewModelScope.launch {
            val response = repository.getInvoiceByUserId(userId, pageIndex, pageSize, token)
            onResponse(response)
        }
    }

    fun addInvoice(
        data: Invoice,
        onResponse: (response: ServiceResponse<Invoice>) -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.addNewInvoice(data, token)
            onResponse(response)
        }
    }


    private fun incrementPage() {
        pageIndex.value = pageIndex.value + 1
    }

    fun onScrollPositionChange(position: Int) {
        scrollPosition = position
    }

    private fun appendItems(items: List<Invoice>) {
        var current = ArrayList(dataList.value)
        current.addAll(items)
        dataList.value = current
    }

    fun nextPage() {
        viewModelScope.launch {
            if ((scrollPosition + 1) >= (pageIndex.value * pageSize)) {
                isLoading.value = true
                incrementPage()
                if (pageIndex.value > 0) {
                    getInvoiceByUserId(userId, pageIndex.value, pageSize) { response ->
                        if (response.status == "OK" && response.data!!.isNotEmpty()) {
                            appendItems(response.data!!)
                        }
                        isLoading.value = false
                    }
                }
            }
        }
    }
}

//eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhc2RmYXNkZiIsImV4cCI6MTY1MDIzOTc1NiwiaWF0IjoxNjUwMjIxNzU2fQ.Gbca98Yj16TTgl5WGyzx6JsiXyF3eXWRzuxPNFMIoRYv4sj_cMxm-M45lvZqjcFrVO1KkAgAvRc89f3WhpYZ_A


//eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTEiLCJleHAiOjE2NTA1Mzg3NDcsImlhdCI6MTY1MDM1ODc0N30._4xrIuHY_iTvFUU7tUNaoGhWqE3QSAjKq6dn8YgPQc-Df-17xbb9TT3EF1M2FGqYq2oDnZh_suo95OmVLeCILw