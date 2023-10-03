package com.github.ebrahimi2723.onlineshop.repositories.sites

import com.github.ebrahimi2723.onlineshop.models.site.Content
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

// when use @Repository you do not need to implement CRUD function
// because @Repository do it all without any implementation
// just define in PagingAndSortingRepository<YourEntityType,YourIDType>

@Repository
interface ContentRepository:PagingAndSortingRepository<Content ,Long>{
    override fun findAll(): List<Content>
    fun findFirstByTitle(string: String):Content?
}