package com.linggash.kotlin.restfulmobil.model

import java.util.*

data class WeightedProductResponse (

    val id: String,

    val name: String,

    val brand: String,

    val price: Long,

    val performance: Int,

    val security: Int,

    val convenience: Int,

    val appearance: Int,

    val efficiency: Int,

    val wp: Double?,

    val image: String?,

    val createdAt: Date,

    val updatedAt: Date?,
)