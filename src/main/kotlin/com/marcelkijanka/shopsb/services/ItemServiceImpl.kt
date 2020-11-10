package com.marcelkijanka.shopsb.services

import com.marcelkijanka.shopsb.models.Item
import com.marcelkijanka.shopsb.util.BasicCrud
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.*

//@Autowhired not needed because class has only 1 constructor
@Service
class ItemServiceImpl(val itemsService: ItemsService): BasicCrud<Item, String> {
    override fun getAll(pageable: Pageable): Page<Item> =  itemsService.findAll(pageable)

    override fun getById(id: String): Optional<Item> = itemsService.findById(id)

    override fun insert(obj: Item): Item = itemsService.insert(obj)

    @Throws(IllegalArgumentException::class)
    override fun update(obj: Item): Item {
        return if (itemsService.existsById(obj.id.toString()))
            //If there is a relation ex. Item->Seller, we should re-insert Seller from DB to avoid inconsistency (obj.apply{this.seller = sellerService.find....})
            itemsService.save(obj)
        else throw IllegalArgumentException("Item not exists in db")
    }

    override fun deleteById(id: String): Optional<Item> {
        return itemsService.findById(id).apply{
            this.ifPresent{item ->
                itemsService.delete(item)
            }
        }
    }
}