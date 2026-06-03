package com.pucetec.ae1462exam1.entities

import jakarta.persistence.*

@Entity
@Table(name = "books")
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var title: String,
    var author: String,
    var priceUsd: Double
)