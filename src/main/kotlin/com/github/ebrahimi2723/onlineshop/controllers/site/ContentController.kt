package com.github.ebrahimi2723.onlineshop.controllers.site

import com.github.ebrahimi2723.onlineshop.models.site.Content
import com.github.ebrahimi2723.onlineshop.services.site.ContentService
import com.github.ebrahimi2723.onlineshop.utils.NotFoundException
import com.github.ebrahimi2723.onlineshop.utils.ResponseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/content")
class ContentController {

    @Autowired
    lateinit var service: ContentService

    //  http://localhost:8080/api/content
    @GetMapping("")
    fun getAll(): ResponseService<Content> {

        val data = service.getAll()
        return if (data.isEmpty()) ResponseService(
            data = null,
            status = HttpStatus.NO_CONTENT,
            masseage = "NO CONTENT",
            totalCount = 0
        ) else ResponseService(data = data, status = HttpStatus.OK, masseage = "", totalCount = data.size)

    }

//    //http://localhost:8080/api/content/1
//    @GetMapping("/{id}")
//    fun getById(@PathVariable id: Long): ResponseService<Content>? {
//
//        val data = service.getById(id)
//        return if (data != null) {
//            ResponseService(data = listOf(data), status = HttpStatus.OK, masseage = "", totalCount = 1)
//        } else {
//            ResponseService(null, HttpStatus.NOT_FOUND, masseage = HttpStatus.NOT_FOUND.toString(), 0)
//        }
//    }

    // http://localhost:8080/api/content/title=title
    @GetMapping("/{title}")
    fun getByTitle(@PathVariable title:String):ResponseService<Content>{
        return try {
            val data = service.getByTitle(title)
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        }
    }



}