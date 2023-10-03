package com.github.ebrahimi2723.onlineshop.services.products

import com.github.ebrahimi2723.onlineshop.models.products.Color
import com.github.ebrahimi2723.onlineshop.repositories.products.ColorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ColorService {


    @Autowired
    lateinit var repository: ColorRepository

    fun getById(id: Long): Color? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }


    fun getAll(): List<Color> {
        return repository.findAll()
    }


    fun getAllCount(): Long {
        return repository.count()
    }


}