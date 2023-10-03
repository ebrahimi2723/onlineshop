package com.github.ebrahimi2723.onlineshop.services.invoices

import com.github.ebrahimi2723.onlineshop.models.invoices.InvoiceItem
import com.github.ebrahimi2723.onlineshop.models.invoices.Transaction
import com.github.ebrahimi2723.onlineshop.repositories.invoices.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService {

    @Autowired
    lateinit var repository : TransactionRepository

    fun insert(data: Transaction):Transaction?{
        return repository.save(data)
    }

    fun getById(id: Long): Transaction? {
        return if (repository.findById(id).isEmpty) null else repository.findById(id).get()
    }

    fun update (data:Transaction):Transaction?{
        val oldData = getById(data.id)?: return null
        oldData.refId = data.refId
        oldData.status = data.status
        return repository.save(oldData)

    }


}