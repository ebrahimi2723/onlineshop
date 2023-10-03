package com.github.ebrahimi2723.onlineshop.services.products

import com.github.ebrahimi2723.onlineshop.models.products.ProductCategory
import com.github.ebrahimi2723.onlineshop.repositories.products.ProductCategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductCategoryService {

    @Autowired
    lateinit var repository: ProductCategoryRepository

    fun getById(id: Long): ProductCategory? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }


    fun getAll(): List<ProductCategory> {

        return repository.findAll()
    }


    fun getAllCount(): Long {
        return repository.count()
    }

}