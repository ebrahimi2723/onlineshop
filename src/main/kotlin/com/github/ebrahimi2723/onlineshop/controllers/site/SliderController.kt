package com.github.ebrahimi2723.onlineshop.controllers.site

import com.github.ebrahimi2723.onlineshop.models.site.Slider
import com.github.ebrahimi2723.onlineshop.services.site.SliderService
import com.github.ebrahimi2723.onlineshop.utils.NotFoundException
import com.github.ebrahimi2723.onlineshop.utils.ResponseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


// http://localhost:8080/api/slider
@RestController
@RequestMapping("api/slider")
class SliderController {

    @Autowired
     lateinit var service: SliderService

    // http://localhost:8080/api/slider
    @GetMapping("")
    fun getAll(): ResponseService<Slider> {

        val data: List<Slider>? = service.getAll().ifEmpty { null }
        return if (data != null) {
            ResponseService(data = data, HttpStatus.OK, "",data.size)
        } else {
            ResponseService(data = null, status = HttpStatus.NO_CONTENT, masseage =HttpStatus.NO_CONTENT.toString(), totalCount = 0)
        }

    }

    // http://localhost:8080/api/slider/1
    @GetMapping("/{id}")
    fun getById(@PathVariable id:Long):ResponseService<Slider>{

        return try {
            val data = service.getById(id)
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        }

    }




}