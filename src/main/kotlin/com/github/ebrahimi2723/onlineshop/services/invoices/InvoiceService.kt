package com.github.ebrahimi2723.onlineshop.services.invoices

import com.github.ebrahimi2723.onlineshop.models.invoices.Invoice
import com.github.ebrahimi2723.onlineshop.models.invoices.InvoiceStatus
import com.github.ebrahimi2723.onlineshop.models.invoices.Transaction
import com.github.ebrahimi2723.onlineshop.repositories.invoices.InvoiceRepository
import com.github.ebrahimi2723.onlineshop.utils.NotFoundException
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


    fun insert(data: Invoice): Invoice? {
        if (data.items == null || data.items!!.isEmpty())
            throw NotFoundException("invoiceItems is Empty")
        if (data.user == null || data.user!!.id <=0)
            throw NotFoundException("Invalid UserID")
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


    fun update(data: Invoice): Invoice? {
        val oldData = getById(data.id) ?: return null
        oldData.paymentDate = data.paymentDate
        oldData.status = data.status
        return repository.save(oldData)

    }

    fun getById(id: Long): Invoice? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }


    fun getAllByUserId(userId: Long, pageIndex: Int, pageSize: Int): List<Invoice> {
        val pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        return repository.findAllByUserId(userId, pageRequest)
    }


}