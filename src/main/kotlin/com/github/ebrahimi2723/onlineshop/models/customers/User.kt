package com.github.ebrahimi2723.onlineshop.models.customers

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.ebrahimi2723.onlineshop.models.invoices.Invoice
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var userName: String = "",
    var password: String = "",

    //define foreign key    one to one
    // connect to tbl customer
//    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null,

    // connect to tbl user
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    var invoices: Set<Invoice>? = null


)