package com.github.ebrahimi2723.onlineshop.models.products

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.ebrahimi2723.onlineshop.models.products.Product
import javax.persistence.*


@Entity
data class ProductCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0 ,
    var title:String = "",
    var image:String ="",

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    var products : Set<Product>? = null
)