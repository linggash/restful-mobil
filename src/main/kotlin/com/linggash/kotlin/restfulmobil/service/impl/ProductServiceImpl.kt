package com.linggash.kotlin.restfulmobil.service.impl

import com.linggash.kotlin.restfulmobil.entity.Product
import com.linggash.kotlin.restfulmobil.model.CreateProductRequest
import com.linggash.kotlin.restfulmobil.model.ProductResponse
import com.linggash.kotlin.restfulmobil.repository.ProductRepository
import com.linggash.kotlin.restfulmobil.service.ProductService
import com.linggash.kotlin.restfulmobil.validation.ValidationUtil
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtil: ValidationUtil
    ) : ProductService {

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validationUtil.validate(createProductRequest)

        val product = Product(
            id = createProductRequest.id!!,
            name = createProductRequest.name!!,
            brand = createProductRequest.brand!!,
            price = createProductRequest.price!!,
            performance = createProductRequest.performance!!,
            security = createProductRequest.security!!,
            convenience = createProductRequest.convenience!!,
            appearance = createProductRequest.appearance!!,
            efficiency = createProductRequest.efficiency!!,
            createdAt = Date(),
            updatedAt = null
        )

        productRepository.save(product)

        return ProductResponse(
            id = product.id,
            name = product.name,
            brand = product.brand,
            price = product.price,
            performance = product.performance,
            security = product.security,
            convenience = product.convenience,
            appearance = product.appearance,
            efficiency = product.efficiency,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }
}