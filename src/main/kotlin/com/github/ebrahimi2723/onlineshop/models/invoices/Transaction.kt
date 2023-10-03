package com.github.ebrahimi2723.onlineshop.models.invoices

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*


@Entity
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,
    var authority:String = "",
    var status: Int = 0,
    var refId:String = "",

    //connect to tbl invoice
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    var invoice: Invoice? = null

    )