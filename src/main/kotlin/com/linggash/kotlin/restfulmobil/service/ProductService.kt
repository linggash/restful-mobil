package com.linggash.kotlin.restfulmobil.service

import com.linggash.kotlin.restfulmobil.model.*

interface ProductService {

    fun create(createProductRequest: CreateProductRequest): ProductResponse

    fun get(id: String): ProductResponse

    fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse

    fun delete(id: String)

    fun list(listProductRequest: ListProductRequest): List<ProductResponse>

    fun uploadImage(uploadImageRequest: UploadImageRequest)
}