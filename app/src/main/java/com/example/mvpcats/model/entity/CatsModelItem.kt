package com.example.mvpcats.model.entity

data class CatsModelItem(
    val breeds: List<Any>,
    val categories: List<Category>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)