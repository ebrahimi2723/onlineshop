package com.github.ebrahimi2723.onlineshop.utils

import com.github.ebrahimi2723.onlineshop.config.JwtTokenUtils
import javax.servlet.http.HttpServletRequest

class UserUtils {
    companion object {
         fun getCurrentUserFromJwtToken(jwtTokenUtils: JwtTokenUtils, request: HttpServletRequest): String {
            val header = request.getHeader("Authorization")
            if (header == null || !header.lowercase()
                    .startsWith("bearer")
            ) throw Exception("Please set Bearer token")

            return jwtTokenUtils.getUsernameFromToken(header.substring(7))
        }
    }

}