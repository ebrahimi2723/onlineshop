package com.github.ebrahimi2723.onlineshop.models.products

import org.hibernate.annotations.ColumnDefault
import javax.persistence.*


@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var title: String = "",
    var image: String = "",
    var visitCount: Int = 0,
    var addDate: String = "",
    var description: String = "",
    var price:Long = 0,

    @ManyToMany
    @JoinColumn(name = "color_id")
    var colors: Set<Color>? = null,

    @ManyToOne
    var category: ProductCategory? = null,

    @ManyToMany
    var sizes :Set<Size>? = null
)