package com.github.ebrahimi2723.onlineshop.repositories.invoices


import com.github.ebrahimi2723.onlineshop.models.invoices.Invoice
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

// when use @Repository you do not need to implement CRUD function
// because @Repository do it all without any implementation
// just define in PagingAndSortingRepository<YourEntityType,YourIDType>

@Repository
interface InvoiceRepository:PagingAndSortingRepository<Invoice,Long>{

    // hibernate query
    // user in below query is define in invoice model
    @Query("from Invoice where user.id =:userId")
    fun findAllByUserId(userId:Long , pageable:Pageable):List<Invoice>

}