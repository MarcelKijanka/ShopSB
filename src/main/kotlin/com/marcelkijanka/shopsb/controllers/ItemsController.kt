package com.marcelkijanka.shopsb.controllers

import com.marcelkijanka.shopsb.models.Item
import com.marcelkijanka.shopsb.services.ItemsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
//jeśli dobrze rozumiem tylko `/items` jest charakterystyczne dla tego controllera, a `api/` jest wspólnym subpathem dla całego backendu. 
//Jeśli tak jest to `/api` możesz sobie ustawić w konfiguracji springa żeby nie pisać tego w każdym controllerze
//https://stackoverflow.com/questions/28006501/how-to-specify-prefix-for-all-controllers-in-spring-boot
@RequestMapping("api/items")
class ItemsController(private val itemsService: ItemsService) {
//brakuje mi w tym API konsekwencji, sprawdź sobie RESTful API, CRUD API i użycie http verbs
    @GetMapping
    fun getAll(pageable: Pageable): Page<Item> = itemsService.getAll(pageable)

    @GetMapping("/id/{id}")
    fun getById(@PathVariable id: String): Optional<Item> = itemsService.getById(id)

    @GetMapping("/name/{name}")
    fun getByName(@PathVariable name: String): List<Item> = itemsService.getByName(name)

    @PostMapping("/insert")
    fun insert(@RequestBody item: Item): Item = itemsService.insert(item)

    @PutMapping("/update")
    fun update(@RequestBody updatedItem: Item): Item = itemsService.update(updatedItem)

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: String): Optional<Item> = itemsService.deleteById(id)
}