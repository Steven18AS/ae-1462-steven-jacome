package com.pucetec.ae1462exam1.dto

data class BookRequest(
    val title: String,
    val author: String,
    val priceUsd: Double
)