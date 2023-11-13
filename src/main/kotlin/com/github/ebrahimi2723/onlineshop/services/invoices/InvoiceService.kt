package com.github.ebrahimi2723.onlineshop.services.invoices

import com.github.ebrahimi2723.onlineshop.models.invoices.Invoice
import com.github.ebrahimi2723.onlineshop.models.invoices.InvoiceStatus
import com.github.ebrahimi2723.onlineshop.models.invoices.Transaction
import com.github.ebrahimi2723.onlineshop.repositories.invoices.InvoiceRepository
import com.github.ebrahimi2723.onlineshop.services.customers.UserService
import com.github.ebrahimi2723.onlineshop.utils.NotFoundException
import com.github.ebrahimi2723.onlineshop.utils.UserUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.Calendar


@Service
class InvoiceService {

    @Autowired
    private lateinit var repository: InvoiceRepository

    @Autowired
    private lateinit var invoiceItemService: InvoiceItemService

    @Autowired
    private lateinit var userService: UserService


    fun insert(data: Invoice, currentUser: String): Invoice? {


        if (data.items == null || data.items!!.isEmpty())
            throw NotFoundException("invoiceItems is Empty")
        if (data.user == null || data.user!!.id <= 0)
            throw NotFoundException("Invalid UserID")
        // check token
        val user = userService.getByUsername(currentUser)
        if (user!!.id != data.user!!.id) throw Exception("you do not have permission to change info")


        data.status = InvoiceStatus.NotPayed
        val dt = Calendar.getInstance()
        data.addDate =
            "${dt.get(Calendar.YEAR)}-${dt.get(Calendar.MONTH)}-${dt.get(Calendar.DAY_OF_MONTH)} ${dt.get(Calendar.HOUR)}:${
                dt.get(Calendar.MINUTE)
            }:${dt.get(Calendar.SECOND)}"
        data.paymentDate = ""
        data.transactions = null
        data.items!!.forEach {
            invoiceItemService.addItem(it)
        }
        return repository.save(data)
    }


    fun update(data: Invoice,currentUser: String): Invoice? {
        val oldData = getById(data.id,currentUser) ?: return null
        oldData.paymentDate = data.paymentDate
        oldData.status = data.status
        return repository.save(oldData)

    }

    fun getById(id: Long,currentUser: String): Invoice? {

        val data = repository.findById(id)
        if (data.isEmpty) return null
        val user = userService.getByUsername(currentUser)
        if (user!!.id != data.get().id) throw Exception("you do not have permission to read data")
        return data.get()
    }


    fun getAllByUserId(userId: Long, pageIndex: Int, pageSize: Int, currentUser: String): List<Invoice> {
        val user = userService.getByUsername(currentUser)
        if (user!!.id != userId) throw Exception("you don't permission to read data")
        val pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        return repository.findAllByUserId(userId, pageRequest)
    }


}