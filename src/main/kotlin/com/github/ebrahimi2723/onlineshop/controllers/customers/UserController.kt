package com.github.ebrahimi2723.onlineshop.controllers.customers

import com.github.ebrahimi2723.onlineshop.config.JwtTokenUtils
import com.github.ebrahimi2723.onlineshop.models.customers.User
import com.github.ebrahimi2723.onlineshop.services.customers.UserService
import com.github.ebrahimi2723.onlineshop.utils.NotFoundException
import com.github.ebrahimi2723.onlineshop.utils.ResponseService
import com.github.ebrahimi2723.onlineshop.utils.UserUtils
import com.github.ebrahimi2723.onlineshop.viewmodels.UserViewModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import com.github.ebrahimi2723.onlineshop.utils.UserUtils.Companion.getCurrentUserFromJwtToken
import io.jsonwebtoken.JwtException

@RestController
@RequestMapping("api/user")
class UserController {


    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var jwtTokenUtils: JwtTokenUtils


    @GetMapping("")
    fun getCurrentUser(request: HttpServletRequest): ResponseService<User> {
        val currentUser = getCurrentUserFromJwtToken(jwtTokenUtils,request)
        val data = userService.getByUsername(currentUser)
        return if (data != null) ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 0) else {
            ResponseService(null, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.toString(), totalCount = 1)
        }
    }


    @PostMapping("/register")
    fun registerUser(@RequestBody user: UserViewModel): ResponseService<User> {

        return try {
            val data = userService.insert(user.getUser())
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        }
        catch (e: JwtException){
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)

        }


    }


    @PutMapping("/update")
    fun editUser(@RequestBody user: UserViewModel, request: HttpServletRequest): ResponseService<User> {

        return try {
            // check jwt token
            val currentUser = getCurrentUserFromJwtToken(jwtTokenUtils,request)

            //check data
            val data = userService.update(user.getUser(), currentUser)
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        }
        catch (e:Exception){
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)

        }


    }


    @PutMapping("/change-password")
    fun changePassword(@RequestBody user: UserViewModel, request: HttpServletRequest): ResponseService<User> {

        return try {
            val currentUser = getCurrentUserFromJwtToken(jwtTokenUtils,request)
            val data = userService.changePassword(user.getUser(), user.repeatPassword, user.currentPassword,currentUser)
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        }
        catch (e:Exception){
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)

        }
    }



    @PostMapping("/login")
    fun login(@RequestBody user: UserViewModel): ResponseService<UserViewModel> {

        return try {
            val data = userService.getUserByUserNameAndPassword(user.userName, user.password)
                ?: throw NotFoundException("can't find user")

            val vm = UserViewModel(data)
            vm.token = jwtTokenUtils.generateToken(vm)!!
            ResponseService(listOf(vm), HttpStatus.OK, masseage = "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message!!.toString(), totalCount = 0)
        }
        catch (e:Exception){
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)

        }

    }

}