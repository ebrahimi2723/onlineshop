package com.github.ebrahimi2723.onlineshop.models.invoices

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.ebrahimi2723.onlineshop.models.customers.User
import javax.persistence.*

@Entity
data class Invoice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var status: InvoiceStatus = InvoiceStatus.NotPayed,
    var addDate: String = "",
    var paymentDate: String = "",

    // connect to tbl user
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null,

    //connect to tbl InvoiceItems
    @OneToMany(mappedBy = "invoice")
    var items : Set<InvoiceItem>? = null,

   //connect to tbl transaction\
    @OneToMany(mappedBy = "invoice")
   var transactions: Set<Transaction>? = null

)
