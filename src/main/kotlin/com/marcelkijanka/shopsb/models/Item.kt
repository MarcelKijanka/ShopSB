package com.marcelkijanka.shopsb.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document
data class Item (
    @Id
    val id: ObjectId = ObjectId.get(),
    val name: String,
    val price: BigDecimal? = null,
    val date: String
)
