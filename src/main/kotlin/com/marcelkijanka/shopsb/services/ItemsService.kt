package com.marcelkijanka.shopsb.services

import com.marcelkijanka.shopsb.models.Item
import com.marcelkijanka.shopsb.util.BasicCrud
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.*

//@Autowired not needed because class has only 1 constructor
@Service
class ItemsService(val itemsDAO: ItemsDAO): BasicCrud<Item, String> {
    override fun getAll(pageable: Pageable): Page<Item> =  itemsDAO.findAll(pageable)

    override fun getById(id: String): Optional<Item> = itemsDAO.findById(id)

    override fun insert(obj: Item): Item = itemsDAO.insert(obj)

    fun getByName(name: String): List<Item> = itemsDAO.findAllByName(name)

    @Throws(IllegalArgumentException::class)
    override fun update(obj: Item): Item {
        return if (itemsDAO.existsById(obj.id))
            //If there is a relation ex. Item->Seller, we should re-insert Seller from DB to avoid inconsistency (obj.apply{this.seller = sellerService.find....})
            itemsDAO.save(obj)
        else throw IllegalArgumentException("Item not exists in db")
    }

    override fun deleteById(id: String): Optional<Item> {
        return itemsDAO.findById(id).apply{
            this.ifPresent{item ->
                itemsDAO.delete(item)
            }
        }
    }
}