package com.github.ebrahimi2723.onlineshop.models.site

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
data class Blog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val image: String ="",
    val title: String = "",
    val subTile: String = "",
    val description: String = "",
    val visitCount: Int = 0,
    val addDate: String =""
)
