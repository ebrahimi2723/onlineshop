package com.github.ebrahimi2723.onlineshop.viewmodels

import com.github.ebrahimi2723.onlineshop.models.customers.Customer
import com.github.ebrahimi2723.onlineshop.models.customers.User

data class UserViewModel(

    var id:Long,
    var userName:String = "",
    var password:String = "",
    var repeatPassword:String ="",
    var currentPassword:String ="",
    var firstName:String="",
    var lastName:String ="",
    var address:String ="",
    var phoneNumber:String="",
    var postalCode:String="",
    var customerId:Long = 0

){


    fun getUser():User{
        return User(
            id,
            userName,
            password,
            getCustomer()
        )
    }


    private fun getCustomer():Customer{
        return Customer(
            id = customerId,
            firstName = firstName,
            lastName = lastName,
            address = address,
            postalCode = postalCode,
            phoneNumber = phoneNumber,

        )
    }

}
