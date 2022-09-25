package com.linggash.kotlin.restfulmobil.entity

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "products")
data class Product (

    @Id
    val id: String,

    @Column(name="name")
    var name: String,

    @Column(name="brand")
    var brand: String,

    @Column(name="price")
    var price: Long,

    @Column(name="performance")
    var performance: Int,

    @Column(name="security")
    var security: Int,

    @Column(name="convenience")
    var convenience: Int,

    @Column(name="appearance")
    var appearance: Int,

    @Column(name="efficiency")
    var efficiency: Int,

    @Column(name="created_at")
    var createdAt: Date,

    @Column(name="updated_at")
    var updatedAt: Date?,

    @Column(name="image")
    var image: String?,

    @Column(name="wp")
    var wp: Double?
)