package com.linggash.kotlin.restfulmobil.model

import org.springframework.web.multipart.MultipartFile

data class UploadImageRequest (

    val id: String,

    val file: MultipartFile
)