package com.github.ebrahimi2723.onlineshop.repositories.customers

import com.github.ebrahimi2723.onlineshop.models.customers.User
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*


// when use @Repository you do not need to implement CRUD function
// because @Repository do it all without any implementation
// just define in PagingAndSortingRepository<YourEntityType,YourIDType>

@Repository
interface UserRepository: PagingAndSortingRepository<User, Long>{

    fun findFirstByUserNameAndPassword(userName:String , password:String):User?
    fun findFirstByUserName(userName: String):User?
    fun findFirstById(id:Long):User?
}