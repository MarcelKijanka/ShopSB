package com.marcelkijanka.shopsb.services

import com.marcelkijanka.shopsb.models.Item
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ItemsService:MongoRepository<Item, String>{
    fun findAllByName(name: String): List<Item>
    fun findById(id: Item): List<Item>
}