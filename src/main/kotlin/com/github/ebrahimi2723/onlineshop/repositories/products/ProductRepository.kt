package com.github.ebrahimi2723.onlineshop.repositories.products


import com.github.ebrahimi2723.onlineshop.models.products.Product
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

// when use @Repository you do not need to implement CRUD function
// because @Repository do it all without any implementation
// just define in PagingAndSortingRepository<YourEntityType,YourIDType>

@Repository
interface ProductRepository:PagingAndSortingRepository<Product,Long>{

    override fun findAll(): List<Product>

    // select top 6 * from product order by addDate desc
    fun findTop6ByOrderByAddDateDesc():List<Product>

    // select top 6 * from product order by visitCount desc
    fun findTop6ByOrderByVisitCountDesc():List<Product>
}