package com.linggash.kotlin.restfulmobil.service

import com.linggash.kotlin.restfulmobil.model.CreateProductRequest
import com.linggash.kotlin.restfulmobil.model.ListProductRequest
import com.linggash.kotlin.restfulmobil.model.ProductResponse
import com.linggash.kotlin.restfulmobil.model.UpdateProductRequest

interface ProductService {

    fun create(createProductRequest: CreateProductRequest): ProductResponse

    fun get(id: String): ProductResponse

    fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse

    fun delete(id: String,)

    fun list(listProductRequest: ListProductRequest): List<ProductResponse>
}