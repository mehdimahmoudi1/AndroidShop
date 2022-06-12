package com.example.androidshop.models.invoices

data class Transction(

var id: Long?,
var amount: Int?,
var card_holder: String?,
var code: Int?,
var custom: String?,
var customerPhone: String?,
var orderId: Int?,
var refId: String?,
var refounRequest: String?,
var shaparakRefId: String?,
var transId: String?
)
