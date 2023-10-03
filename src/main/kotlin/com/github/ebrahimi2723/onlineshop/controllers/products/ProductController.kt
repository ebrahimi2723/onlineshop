package com.github.ebrahimi2723.onlineshop.controllers.products

import com.github.ebrahimi2723.onlineshop.models.products.Product
import com.github.ebrahimi2723.onlineshop.services.products.ProductService
import com.github.ebrahimi2723.onlineshop.utils.NotFoundException
import com.github.ebrahimi2723.onlineshop.utils.ResponseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/product")
class ProductController {

    @Autowired
    lateinit var service: ProductService


    @GetMapping("")
    fun getAll(@RequestParam pageIndex: Int, @RequestParam pageSize: Int): ResponseService<Product> {
        val data = service.getAll(pageIndex, pageSize)

        return if (data.isEmpty()) ResponseService(
            data = null,
            status = HttpStatus.NO_CONTENT,
            masseage = HttpStatus.NO_CONTENT.toString(),
            totalCount = 0
        ) else ResponseService(
            data = data,
            status = HttpStatus.OK,
            masseage = "",
            totalCount = data.size
        )
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseService<Product> {
        return try {
            val data = service.getById(id)
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        }
    }

    @GetMapping("/popular")
    fun getPopular(): ResponseService<Product> {
        val data = service.getPopular()
        return if (data.isEmpty()) ResponseService(
            data = null,
            status = HttpStatus.NO_CONTENT,
            masseage = HttpStatus.NO_CONTENT.toString(),
            totalCount = 0
        ) else ResponseService(
            data = data,
            status = HttpStatus.OK,
            masseage = "",
            totalCount = data.size
        )
    }


    @GetMapping("/new")
    fun getNew(): ResponseService<Product> {
        val data = service.getNew()
        return if (data.isEmpty()) ResponseService(
            data = null,
            status = HttpStatus.NO_CONTENT,
            masseage = HttpStatus.NO_CONTENT.toString(),
            totalCount = 0
        ) else ResponseService(
            data = data,
            status = HttpStatus.OK,
            masseage = "",
            totalCount = data.size
        )
    }


}