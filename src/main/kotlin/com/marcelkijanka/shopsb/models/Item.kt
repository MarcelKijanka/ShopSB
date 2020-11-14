package com.marcelkijanka.shopsb.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Item (
    @Id //wiem, ze w swiecie Springa to jest norma, ale z punktu widzenia architektury to że definicja modelu domenowego wie że korzystamy ze Springa to smrodek, a to że jeszcze wie że korzystamy z mongoDb to już jest poważna lipa
    val id: String,
    //jeśli chciałbyś skorzystać z kontroli typów żeby podczas tworzenia obiektu Item nie przypisać np name do id możesz stworzyć sobie dla tych pól value classes (w kotlinie nazywa się to inline class https://kotlinlang.org/docs/reference/inline-classes.html)
    val name: String,
    val price: Double = 0.0
)
