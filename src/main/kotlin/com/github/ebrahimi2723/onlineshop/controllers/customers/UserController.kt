package com.github.ebrahimi2723.onlineshop.controllers.customers

import com.github.ebrahimi2723.onlineshop.models.customers.User
import com.github.ebrahimi2723.onlineshop.services.customers.UserService
import com.github.ebrahimi2723.onlineshop.utils.NotFoundException
import com.github.ebrahimi2723.onlineshop.utils.ResponseService
import com.github.ebrahimi2723.onlineshop.viewmodels.UserViewModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/user")
class UserController {


    @Autowired
    lateinit var userService: UserService


    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseService<User> {
        val data = userService.getById(id)
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


    }


    @PutMapping("/update")
    fun editUser(@RequestBody user: UserViewModel): ResponseService<User> {

        return try {
            val data = userService.update(user.getUser())
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        }


    }


    @PutMapping("/change-password")
    fun changePassword(@RequestBody user: UserViewModel):ResponseService<User>{
        return try {
            val data = userService.changePassword(user.getUser(),user.repeatPassword,user.currentPassword)
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        }
    }


    @PostMapping("/login")
    fun login(@RequestBody user: UserViewModel): ResponseService<User> {
        return try {
            val data = userService.getUserByUserNameAndPassword(user.userName, user.password)
                ?: throw NotFoundException("data not found")
            ResponseService(listOf(data), HttpStatus.OK, "", totalCount = 1)
        } catch (e: NotFoundException) {
            ResponseService(status = HttpStatus.NOT_FOUND, masseage = e.message.toString(), totalCount = 0)
        }

    }


}