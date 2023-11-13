package com.github.ebrahimi2723.onlineshop.config.filter

import com.github.ebrahimi2723.onlineshop.config.JwtTokenUtils
import com.github.ebrahimi2723.onlineshop.services.customers.UserService
import com.github.ebrahimi2723.onlineshop.viewmodels.UserViewModel
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter:Filter {

    @Autowired
    private lateinit var jwtTokenUtils: JwtTokenUtils

    @Autowired
    private lateinit var userService: UserService


    private val excludeUrls = ArrayList<String>()

    private val excludeContainersUrl = ArrayList<String>()

    init {
         excludeUrls.add("/api/user/login")
         excludeUrls.add("/api/user/register")

        excludeContainersUrl.add("/api/color")
        excludeContainersUrl.add("/api/productCategory")
        excludeContainersUrl.add("/api/product")
        excludeContainersUrl.add("/api/blog")
        excludeContainersUrl.add("/api/content")
        excludeContainersUrl.add("/api/slider")
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        try {
            val url = (request as HttpServletRequest).requestURI.lowercase()
            if (excludeUrls.stream().anyMatch{ x -> url == x.lowercase() } || excludeContainersUrl.stream().anyMatch { x -> url.startsWith(x.lowercase()) }){
                chain!!.doFilter(request,response)
                return
            }

            val requestTokenHeader = request.getHeader("Authorization")
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) throw JwtException("request token header does not set")
            val token = requestTokenHeader.substring(7)
            val username  = jwtTokenUtils.getUsernameFromToken(token)
            val userVM = UserViewModel(userService.getByUsername(username)!!)
            if (!jwtTokenUtils.validateToken(token,userVM)) throw JwtException("invalid token")
            chain!!.doFilter(request,response)
        }catch (ex:JwtException){
            (response as HttpServletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,"UnAuthorized")
        }catch (ex: ExpiredJwtException){
            (response as HttpServletResponse).sendError(HttpServletResponse.SC_EXPECTATION_FAILED,ex.message)
        }catch (ex:Exception){
            (response as HttpServletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,ex.message)
        }





    }
}