package com.github.ebrahimi2723.onlineshop.models.customers

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.GenerationType


@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var postalCode: String = "",
    var phoneNumber: String = "",

    //define foreign key >>> one to one
    // @OneToOne(mappedBy = "customer") >>>> customer is name of value that define in User class
    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    var user: User? = null

)