package com.github.ebrahimi2723.onlineshop.controllers.invoices

import com.github.ebrahimi2723.onlineshop.config.JwtTokenUtils
import com.github.ebrahimi2723.onlineshop.models.invoices.Invoice
import com.github.ebrahimi2723.onlineshop.services.invoices.InvoiceService
import com.github.ebrahimi2723.onlineshop.utils.NotFoundException
import com.github.ebrahimi2723.onlineshop.utils.ResponseService
import com.github.ebrahimi2723.onlineshop.utils.UserUtils
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
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("api/invoice")
class InvoiceController {


    @Autowired
    lateinit var service: InvoiceService

    @Autowired
    lateinit var jwtTokenUtils: JwtTokenUtils

    @GetMapping("/user/{userId}")
    fun getAllByUserID(
        @PathVariable userId: Long,
        @RequestParam pageIndex: Int,
        @RequestParam pageSize: Int,
        request: HttpServletRequest
    ): ResponseService<Invoice> {

        return try {
            val currentUser = UserUtils.getCurrentUserFromJwtToken(jwtTokenUtils, request)
            val data = service.getAllByUserId(userId,pageIndex,pageSize,currentUser)

            ResponseService(data, HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        }
        catch (e:Exception){
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)

        }


    }


    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long, request: HttpServletRequest): ResponseService<Invoice> {
        return try {

            val currentUser = UserUtils.getCurrentUserFromJwtToken(jwtTokenUtils,request)
            val data = service.getById(id,currentUser)
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        } catch (e:Exception){
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)

        }
    }

    @PostMapping("/add-invoice")
    fun addInvoice(@RequestBody invoice: Invoice,request: HttpServletRequest): ResponseService<Invoice> {

        return try {
            val currentUser = UserUtils.getCurrentUserFromJwtToken(jwtTokenUtils, request)
            val data = service.insert(invoice,currentUser)
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        } catch (e:Exception){
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)

        }
    }


    @PutMapping("")
    fun update(@RequestBody invoice: Invoice, request: HttpServletRequest): ResponseService<Invoice> {



        return try {
            val currentUser = UserUtils.getCurrentUserFromJwtToken(jwtTokenUtils, request)
            val data = service.update(invoice,currentUser)
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        } catch (e:Exception){
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)

        }
    }

}