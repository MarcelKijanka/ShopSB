package com.marcelkijanka.shopsb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class ShopsbApplication

fun main(args: Array<String>) {
    runApplication<ShopsbApplication>(*args)
}

@RestController
class MainController{
    @GetMapping
    fun index()="Hello World"
}