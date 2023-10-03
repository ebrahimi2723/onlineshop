package com.github.ebrahimi2723.onlineshop.services.site

import com.github.ebrahimi2723.onlineshop.models.site.Content
import com.github.ebrahimi2723.onlineshop.repositories.sites.ContentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ContentService {

    @Autowired
    lateinit var repository: ContentRepository

    fun getById(id: Long): Content? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAll(): List<Content> {
        return repository.findAll()
    }


    fun getByTitle(title: String): Content? {
        return repository.findFirstByTitle(title)
    }


    fun getAllCount(): Long {
        return repository.count()
    }
}