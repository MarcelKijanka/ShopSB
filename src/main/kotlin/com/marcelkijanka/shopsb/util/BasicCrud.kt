package com.marcelkijanka.shopsb.util

import com.marcelkijanka.shopsb.models.Item
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface BasicCrud<T, K> {
    fun getAll(pageable: Pageable): Page<T>
    fun getById(id: K): Optional<T>
    fun insert(obj: T): T
    fun update(obj: T): T
    fun deleteById(id: K): Optional<T>
}