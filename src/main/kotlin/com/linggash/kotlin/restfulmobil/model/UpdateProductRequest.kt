package com.linggash.kotlin.restfulmobil.model

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UpdateProductRequest (

    @field:NotBlank
    val name: String?,

    @field:NotBlank
    val brand: String?,

    @field:NotNull
    @field:Min(value = 1)
    val price: Long?,

    @field:NotNull
    @field:Min(value = 1)
    @field:Max(value = 5)
    val performance: Int?,

    @field:NotNull
    @field:Min(value = 1)
    @field:Max(value = 5)
    val security: Int?,

    @field:NotNull
    @field:Min(value = 1)
    @field:Max(value = 5)
    val convenience: Int?,

    @field:NotNull
    @field:Min(value = 1)
    @field:Max(value = 5)
    val appearance: Int?,

    @field:NotNull
    @field:Min(value = 1)
    @field:Max(value = 5)
    val efficiency: Int?,
)