package com.linggash.kotlin.restfulmobil.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.nio.file.Path
import java.nio.file.Paths

@Configuration
class ImageConfig : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry){
        exposeDirectory("../images/", registry)
    }

    private fun exposeDirectory( dirName: String, resourceHandlerRegistry: ResourceHandlerRegistry){
        val uploadDir: Path = Paths.get(dirName)
        val uploadPath: String = uploadDir.toFile().absolutePath

        if (dirName.startsWith("../")) {
            dirName.replace("../", "")
        }

        resourceHandlerRegistry.addResourceHandler("/$dirName/**").addResourceLocations("file:/$uploadPath/")
    }
}