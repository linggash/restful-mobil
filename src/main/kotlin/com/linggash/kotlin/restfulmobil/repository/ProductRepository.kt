package com.linggash.kotlin.restfulmobil.repository

import com.linggash.kotlin.restfulmobil.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String> {
}