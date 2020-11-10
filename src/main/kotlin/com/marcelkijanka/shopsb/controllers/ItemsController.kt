package com.marcelkijanka.shopsb.controllers

import com.marcelkijanka.shopsb.models.Item
import com.marcelkijanka.shopsb.services.ItemsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/items")
class ItemsController(private val itemsService: ItemsService) {

    @GetMapping
    fun getAll(pageable: Pageable): Page<Item> = itemsService.getAll(pageable)

    @GetMapping("{id}")
    fun getById(@PathVariable id: String): Optional<Item> = itemsService.getById(id)

    @GetMapping("{name}")
    fun getByName(@PathVariable name: String): List<Item> = itemsService.getByName(name)

    @PostMapping("/insert")
    fun insert(@RequestBody item: Item): Item = itemsService.insert(item)

    /*@PostMapping
    fun update(@RequestBody updatedItem: Item): Item = itemsService.update(updatedItem)*/

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: String): Optional<Item> = itemsService.deleteById(id)
}