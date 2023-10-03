package com.github.ebrahimi2723.onlineshop.models.products

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
data class Color(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var title: String = "",
    var hexValue: String = "",

//    @ManyToMany
//    var products : Set<Color>? = null
    )