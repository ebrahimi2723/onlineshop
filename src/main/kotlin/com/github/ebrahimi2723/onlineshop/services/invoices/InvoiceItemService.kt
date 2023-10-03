package com.github.ebrahimi2723.onlineshop.services.invoices

import com.github.ebrahimi2723.onlineshop.models.invoices.InvoiceItem
import com.github.ebrahimi2723.onlineshop.models.products.Product
import com.github.ebrahimi2723.onlineshop.repositories.invoices.InvoiceItemRepository
import com.github.ebrahimi2723.onlineshop.services.products.ProductService
import com.github.ebrahimi2723.onlineshop.utils.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InvoiceItemService {

    @Autowired
    private lateinit var repository: InvoiceItemRepository

    @Autowired
    lateinit var productService: ProductService


    fun getById(id: Long): InvoiceItem? {
        return if (repository.findById(id).isEmpty) null else repository.findById(id).get()
    }


    fun getByInvoiceId(id: Long): List<InvoiceItem> {
        return repository.findAllByInvoiceId(id)
    }


    fun addItem(data: InvoiceItem): InvoiceItem {
        if (data.quantity <= 0)
            throw Exception("Invalid Quantity")

        if (data.product == null || data.product!!.id <= 0)
            throw NotFoundException("Invalid Product")
        val productData = productService.getById(data.product!!.id)
        data.product!!.price
        return repository.save(data)
    }

}