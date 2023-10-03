package com.github.ebrahimi2723.onlineshop.services.products

import com.github.ebrahimi2723.onlineshop.models.products.Product
import com.github.ebrahimi2723.onlineshop.repositories.products.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class ProductService {
    @Autowired
    lateinit var repository:ProductRepository

    fun getById(id:Long): Product? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }


    fun getAll():List<Product>{
        return repository.findAll()
    }

    fun getAll(pageIndex:Int,pageSize:Int):List<Product>{
        val pageRequest = PageRequest.of(pageIndex,pageSize, Sort.by("id"))
        return  repository.findAll(pageRequest).toList()
    }


    fun getNew():List<Product>{
        return repository.findTop6ByOrderByAddDateDesc()
    }

    fun getPopular():List<Product>{
        return repository.findTop6ByOrderByVisitCountDesc()
    }


    fun getAllCount():Long{
        return repository.count()
    }

}