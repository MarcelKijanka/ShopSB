package com.marcelkijanka.shopsb.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Item (
    @Id
    val id: String,
    val name: String,
    val price: Double = 0.0
)
