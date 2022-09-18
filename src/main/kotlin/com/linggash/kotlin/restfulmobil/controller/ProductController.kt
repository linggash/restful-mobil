package com.linggash.kotlin.restfulmobil.controller

import com.linggash.kotlin.restfulmobil.model.CreateProductRequest
import com.linggash.kotlin.restfulmobil.model.ProductResponse
import com.linggash.kotlin.restfulmobil.model.WebResponse
import com.linggash.kotlin.restfulmobil.service.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(val productService: ProductService) {

    @PostMapping(
        value = ["api/products"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductResponse> {
        val productResponse = productService.create(body)

        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }
}