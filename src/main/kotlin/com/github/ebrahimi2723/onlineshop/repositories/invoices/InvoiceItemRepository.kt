package com.github.ebrahimi2723.onlineshop.repositories.invoices


import com.github.ebrahimi2723.onlineshop.models.invoices.InvoiceItem
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

// when use @Repository you do not need to implement CRUD function
// because @Repository do it all without any implementation
// just define in PagingAndSortingRepository<YourEntityType,YourIDType>

@Repository
interface InvoiceItemRepository:PagingAndSortingRepository<InvoiceItem,Long>{

    @Query("from InvoiceItem where invoice.id =: invoiceId")
    fun findAllByInvoiceId(invoiceId:Long):List<InvoiceItem>
}