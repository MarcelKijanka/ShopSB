package com.marcelkijanka.shopsb.services

import com.marcelkijanka.shopsb.models.Item
import org.springframework.data.mongodb.repository.MongoRepository

interface ItemsDAO:MongoRepository<Item, String>{
    fun findAllByName(name: String): List<Item>
}