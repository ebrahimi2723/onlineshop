package com.github.ebrahimi2723.onlineshop.utils

import org.springframework.http.HttpStatus
import java.io.Serializable

data class ResponseService<T>(
    var data: List<T>? = null,
    var status: HttpStatus,
    var masseage: String = "",
    var totalCount:Int
):Serializable