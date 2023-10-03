package com.github.ebrahimi2723.onlineshop.controllers.products

import com.github.ebrahimi2723.onlineshop.models.products.Color
import com.github.ebrahimi2723.onlineshop.services.products.ColorService
import com.github.ebrahimi2723.onlineshop.utils.NotFoundException
import com.github.ebrahimi2723.onlineshop.utils.ResponseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/color")
class ColorController {

    @Autowired
    lateinit var service: ColorService


    // http//localhost:8080/api/color
    @GetMapping("")
    fun getAll(): ResponseService<Color> {
        val data = service.getAll()

        return if (data.isEmpty()) ResponseService(
            data = null,
            status = HttpStatus.NO_CONTENT,
            masseage = HttpStatus.NO_CONTENT.toString(),
            totalCount = 0
        ) else ResponseService(data = data, HttpStatus.OK, masseage = "", totalCount = data.size)
    }



    // http://localhost:8080/api/Color/1
    @GetMapping("/{id}")
    fun getById(@PathVariable id:Long):ResponseService<Color>{

        return try {
            val data = service.getById(id)
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        }

    }


}