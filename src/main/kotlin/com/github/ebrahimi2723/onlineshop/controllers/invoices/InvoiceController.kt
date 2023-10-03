package com.github.ebrahimi2723.onlineshop.controllers.invoices

import com.github.ebrahimi2723.onlineshop.models.invoices.Invoice
import com.github.ebrahimi2723.onlineshop.services.invoices.InvoiceService
import com.github.ebrahimi2723.onlineshop.utils.NotFoundException
import com.github.ebrahimi2723.onlineshop.utils.ResponseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/invoice")
class InvoiceController {


    @Autowired
    lateinit var service: InvoiceService

    @GetMapping("/user/{userId}")
    fun getAllByUserID(
        @PathVariable userId: Long,
        @RequestParam pageIndex: Int,
        @RequestParam pageSize: Int
    ): ResponseService<Invoice> {
        val data = service.getAllByUserId(userId, pageIndex, pageSize)
        return if (data.isEmpty()) ResponseService(
            data,
            HttpStatus.NOT_FOUND,
            HttpStatus.NOT_FOUND.toString(),
            0
        ) else ResponseService(data, HttpStatus.OK, "", data.size)

    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseService<Invoice> {
        return try {
            val data = service.getById(id)
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        }
    }

    @PostMapping("/add-invoice")
    fun addInvoice(@RequestBody invoice: Invoice): ResponseService<Invoice> {

        return try {
            val data = service.insert(invoice)
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        }
    }


    @PutMapping("")
    fun update(@RequestBody invoice: Invoice):ResponseService<Invoice>{
        return try {
            val data = service.update(invoice)
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        }
    }

}