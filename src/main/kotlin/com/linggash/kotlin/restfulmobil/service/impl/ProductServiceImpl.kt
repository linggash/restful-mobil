package com.linggash.kotlin.restfulmobil.service.impl

import com.linggash.kotlin.restfulmobil.entity.Product
import com.linggash.kotlin.restfulmobil.error.NotFoundException
import com.linggash.kotlin.restfulmobil.model.CreateProductRequest
import com.linggash.kotlin.restfulmobil.model.ProductResponse
import com.linggash.kotlin.restfulmobil.model.UpdateProductRequest
import com.linggash.kotlin.restfulmobil.repository.ProductRepository
import com.linggash.kotlin.restfulmobil.service.ProductService
import com.linggash.kotlin.restfulmobil.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
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

        return convertProductToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = findProductByIdOrThrowNotFound(id)
        return convertProductToProductResponse(product)
    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = findProductByIdOrThrowNotFound(id)

        validationUtil.validate(updateProductRequest)

        product.apply {
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            brand = updateProductRequest.brand!!
            price = updateProductRequest.price!!
            performance = updateProductRequest.performance!!
            security = updateProductRequest.security!!
            convenience = updateProductRequest.convenience!!
            appearance = updateProductRequest.appearance!!
            efficiency = updateProductRequest.efficiency!!
            updatedAt = Date()
        }

        productRepository.save(product)

        return convertProductToProductResponse(product)
    }

    override fun delete(id: String) {
        val product = findProductByIdOrThrowNotFound(id)
        productRepository.delete(product)
    }

    private fun findProductByIdOrThrowNotFound(id: String): Product {
        val product = productRepository.findByIdOrNull(id)

        if(product == null){
            throw NotFoundException()
        }else {
            return product
        }
    }

    private fun convertProductToProductResponse(product: Product): ProductResponse {
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