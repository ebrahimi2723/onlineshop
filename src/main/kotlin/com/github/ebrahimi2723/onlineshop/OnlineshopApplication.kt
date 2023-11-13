package com.github.ebrahimi2723.onlineshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.view.InternalResourceViewResolver




@SpringBootApplication
//  use  below  annotation for swagger2
@EnableWebMvc
class OnlineshopApplication


fun main(args: Array<String>) {
	runApplication<OnlineshopApplication>(*args)
}
