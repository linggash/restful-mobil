package com.linggash.kotlin.restfulmobil.service

import com.linggash.kotlin.restfulmobil.model.CreateProductRequest
import com.linggash.kotlin.restfulmobil.model.ProductResponse

interface ProductService {

    fun create(createProductRequest: CreateProductRequest): ProductResponse

    fun get(id: String): ProductResponse
}