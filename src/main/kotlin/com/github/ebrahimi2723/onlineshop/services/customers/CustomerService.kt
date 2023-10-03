package com.github.ebrahimi2723.onlineshop.services.customers

import com.github.ebrahimi2723.onlineshop.models.customers.Customer
import com.github.ebrahimi2723.onlineshop.models.invoices.Invoice
import com.github.ebrahimi2723.onlineshop.repositories.customers.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService {


    @Autowired
    lateinit var repository:CustomerRepository


    fun insert(data: Customer): Customer?{
        if (data.firstName.isEmpty()) throw Exception("Please Enter your Name")
        if (data.lastName.isEmpty()) throw Exception("Please Enter your lastname")
        if (data.phoneNumber.isEmpty()) throw Exception("Please Enter your phone number")

        return repository.save(data)
    }


    fun update (data: Customer): Customer?{
        val oldData = getById(data.id)?: return null
        oldData.firstName = data.firstName
        oldData.lastName = data.lastName
        oldData.postalCode = data.postalCode
        oldData.phoneNumber = data.phoneNumber
        return repository.save(oldData)

    }


    fun getById(id:Long):Customer?{
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

}