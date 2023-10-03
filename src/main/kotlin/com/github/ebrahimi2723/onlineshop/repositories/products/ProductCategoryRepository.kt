package com.github.ebrahimi2723.onlineshop.repositories.products

import com.github.ebrahimi2723.onlineshop.models.products.ProductCategory
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

// when use @Repository you do not need to implement CRUD function
// because @Repository do it all without any implementation
// just define in PagingAndSortingRepository<YourEntityType,YourIDType>

@Repository
interface ProductCategoryRepository :PagingAndSortingRepository<ProductCategory,Long>{

    override fun findAll():List<ProductCategory>

}