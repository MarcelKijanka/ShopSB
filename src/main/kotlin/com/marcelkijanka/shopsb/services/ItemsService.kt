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

    //endpoint używający tej metody to PUT, czyli to zachowanie nie zgadza się z definicją HTTP#PUT
    //btw dobrą praktyką jest tworzenie wyjątków domenowych a nie tylko przekazywanie stringa z opisem błędu, bo potem łatwiej jest np definiować mapowanie z typu wyjątku na kod http
    @Throws(IllegalArgumentException::class)
    override fun update(obj: Item): Item {
        return if (itemsDAO.existsById(obj.id))
            //If there is a relation ex. Item->Seller, we should re-insert Seller from DB to avoid inconsistency (obj.apply{this.seller = sellerService.find....})
            itemsDAO.save(obj)
            //rzucanie wyjątków jest słabe bo znacznie utrudnia zrozumienie kodu i stwierdzenie co dana funkcja robi i jak może się skończyć jej wykonanie. Moim zdaniem o wiele lepszym podejściem jest używanie wrapperów wartości które definiują czy operacja zakończyła się skucesem czy nie. Przykładami ze świata funkcyjnego są Option, Either i Try (wszystkie są dostępne w bibliotece arrow), masz jeszcze do dyspozycji między innymi Result z kotlina, lub Result z libki https://github.com/kittinunf/Result
        else throw IllegalArgumentException("Item not exists in db")
    }

    //tbh odradzam stosowanie javowego Optional'a, kojarzę że są z nim spore poblemy w serializacji i deserializacji + jego zachowanie jest w pewnym sensie dziwne https://www.sitepoint.com/how-optional-breaks-the-monad-laws-and-why-it-matters/ . W kotlinie możesz go zastąpić przez użycie operatora `?` (https://kotlinlang.org/docs/reference/null-safety.html) lub Option z libki arrow
    override fun deleteById(id: String): Optional<Item> =
        itemsDAO.findById(id).apply{
            this.ifPresent{item ->
                itemsDAO.delete(item)
            }
        }
    /*
    return i nawais na całej funkcji nie są konieczne 
    
       override fun deleteById(id: String): Optional<Item> =
        itemsDAO.findById(id).apply{
            this.ifPresent{item ->
                itemsDAO.delete(item)
            }
        }
    */
}