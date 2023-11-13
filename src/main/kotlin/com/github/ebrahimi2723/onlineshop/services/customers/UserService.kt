package com.github.ebrahimi2723.onlineshop.services.customers

import com.github.ebrahimi2723.onlineshop.models.customers.User
import com.github.ebrahimi2723.onlineshop.repositories.customers.UserRepository
import com.github.ebrahimi2723.onlineshop.utils.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class UserService {

    @Autowired
    lateinit var repository: UserRepository


    @Autowired
    lateinit var customerService: CustomerService

    fun insert(data: User): User? {
        val checkUserName = repository.findFirstByUserName(data.userName)
        if (checkUserName != null) {
            throw NotFoundException("username already exist")
        } else {

            if (data.userName.isEmpty()) throw NotFoundException("Please Enter username")
            if (data.password.isEmpty()) throw NotFoundException("Please Enter password")
            customerService.insert(data.customer!!)
            val value = repository.save(data)
            value.password = ""
            return value
        }
    }


    fun changePassword(data: User, repeatPassword: String,currentPassword:String ,currentUser: String): User? {

        val username = repository.findFirstByUserName(currentUser)
        if (username == null || data.id != username.id) throw NotFoundException("you dont have permission to change info")
        val checkUserExist = repository.findFirstById(data.id)?: return null
        if (data.password != repeatPassword) throw NotFoundException("password and repeatPassword not match")
        if (checkUserExist.password != currentPassword) throw NotFoundException("currentPassword is invalid")
        if (checkUserExist.password == data.password) throw  NotFoundException("your new password is same as current password select another password")


        // TODO :CHECK PASSWORD STRENGTH
        val oldData = repository.findFirstById(data.id) ?: return null
        oldData.password = data.password
        val value = repository.save(oldData)
        value.password = ""
        return value

    }


    fun update(data: User , currentUser:String): User? {

        val username = repository.findFirstByUserName(userName = currentUser)
        if (username == null || username.id != data.id) throw NotFoundException("you don't have permission to change info")

        val oldCustomer = customerService.update(data.customer!!)
        if (oldCustomer != null) throw NotFoundException("some thing wrong")
//        oldCustomer!!.postalCode = data.customer!!.postalCode
//        oldCustomer.firstName = data.customer!!.firstName
//        oldCustomer.lastName = data.customer!!.lastName
//        oldCustomer.address = data.customer!!.address
//        oldCustomer.phoneNumber = data.customer!!.phoneNumber

//        customerService.update(oldCustomer)
        return data

    }

    fun getByUsername(username: String): User? {

        if (username.isEmpty()) throw NotFoundException("username can't be empty")
        return repository.findFirstByUserName(username)

    }

    fun getUserByUserNameAndPassword(userName: String, password: String): User? {

        if (userName.isEmpty() || password.isEmpty()) throw NotFoundException("user and password can't be empty")

        val data = repository.findFirstByUserNameAndPassword(userName, password)
        if (data != null) {
            data.password = ""
        }

        return data
    }
}