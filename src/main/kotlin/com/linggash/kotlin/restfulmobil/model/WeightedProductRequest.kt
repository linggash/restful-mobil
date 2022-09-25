package com.linggash.kotlin.restfulmobil.model

data class WeightedProductRequest (

    val harga: Int,

    val performa: Int,

    val keamanan: Int,

    val kenyamanan: Int,

    val tampilan: Int,

    val efisiensi: Int,

    val page: Int = 0,

    val size: Int = 10
)