package com.linggash.kotlin.restfulmobil.service.impl

import com.linggash.kotlin.restfulmobil.entity.Product
import com.linggash.kotlin.restfulmobil.error.DataExistException
import com.linggash.kotlin.restfulmobil.error.NotFoundException
import com.linggash.kotlin.restfulmobil.model.*
import com.linggash.kotlin.restfulmobil.repository.ProductRepository
import com.linggash.kotlin.restfulmobil.service.ProductService
import com.linggash.kotlin.restfulmobil.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import java.util.stream.Collectors
import javax.validation.ConstraintViolationException
import kotlin.math.pow

@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtil: ValidationUtil
    ) : ProductService {

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validationUtil.validate(createProductRequest)

        val vali = productRepository.findByIdOrNull(createProductRequest.id)

        if (vali != null){
            throw DataExistException()
        }

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
            updatedAt = null,
            image = null,
            wp = null
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

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {

        val page = productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        val products:List<Product> = page.get().collect(Collectors.toList())

        return products.map { convertProductToProductResponse(it)}
    }

    override fun uploadImage(uploadImageRequest: UploadImageRequest) {
        val product = findProductByIdOrThrowNotFound(uploadImageRequest.id)

        val downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(uploadImageRequest.id).path(".jpg").toUriString()

        product.apply {
            image = downloadUrl
            updatedAt = Date()
        }
        productRepository.save(product)
    }

    override fun weightedProduct(weightedProductRequest: WeightedProductRequest): List<WeightedProductResponse> {

        val jumlahBobot = weightedProductRequest.harga + weightedProductRequest.performa + weightedProductRequest.keamanan +
                weightedProductRequest.kenyamanan + weightedProductRequest.tampilan + weightedProductRequest.efisiensi

        val harga = weightedProductRequest.harga.toDouble() / jumlahBobot.toDouble() * -1
        val performa = weightedProductRequest.performa.toDouble() / jumlahBobot.toDouble()
        val keamanan = weightedProductRequest.keamanan.toDouble() / jumlahBobot.toDouble()
        val kenyamanan = weightedProductRequest.kenyamanan.toDouble() / jumlahBobot.toDouble()
        val tampilan = weightedProductRequest.tampilan.toDouble() / jumlahBobot.toDouble()
        val efisiensi = weightedProductRequest.efisiensi.toDouble() / jumlahBobot.toDouble()
        val page = productRepository.findAll()
        var products:List<Product> = page
        var vectorS: Double
        var vectorY: Double = 0.0

        page.forEach {
            vectorS = (it.price.toDouble().pow(harga.toDouble()) * (it.performance.toDouble().pow(performa.toDouble())) *
                    (it.security.toDouble().pow(keamanan)) * (it.convenience.toDouble().pow(kenyamanan.toDouble())) *
                    it.appearance.toDouble().pow(tampilan.toDouble()) * it.efficiency.toDouble().pow(efisiensi.toDouble()))
            vectorY += vectorS
            it.wp = vectorS
        }

        products.forEach {
            val hasil = it.wp!! / vectorY
            it.wp = hasil
        }

        products = products.sortedByDescending { it.wp }

//        if (weightedProductRequest.page == 0){
//            val size = products.size - weightedProductRequest.size
//            products = products.dropLast(size)
//        }
//        else{
//            val page = products.size % weightedProductRequest.size
//
//            if (weightedProductRequest.page < page){
//                products = products.drop(weightedProductRequest.size * weightedProductRequest.page)
//                products = products.dropLast(page)
//            }else {
//                throw  NotFoundException()
//            }
//
//        }

        return products.map { convertWeightedProductToProductResponse(it)}
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
    private fun convertWeightedProductToProductResponse(product: Product): WeightedProductResponse {
        return WeightedProductResponse(
            id = product.id,
            name = product.name,
            brand = product.brand,
            price = product.price,
            performance = product.performance,
            security = product.security,
            convenience = product.convenience,
            appearance = product.appearance,
            efficiency = product.efficiency,
            wp = product.wp,
            image = product.image,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }
}