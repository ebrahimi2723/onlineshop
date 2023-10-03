package com.github.ebrahimi2723.onlineshop.models.invoices

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.ebrahimi2723.onlineshop.models.products.Product
import javax.persistence.*


@Entity
data class InvoiceItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product? = null,

    var quantity: Int = 0,
    var unitPrice: Long = 0,

    // connect to tbl invoice
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    var invoice: Invoice? = null
)