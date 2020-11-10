package com.marcelkijanka.shopsb

import com.marcelkijanka.shopsb.services.ItemsDAO
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShopsbApplication(private val itemsDAO: ItemsDAO): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {

    }
}

fun main(args: Array<String>) {
    runApplication<ShopsbApplication>(*args)
}