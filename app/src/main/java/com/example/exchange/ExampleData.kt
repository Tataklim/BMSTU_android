package com.example.exchange

import com.squareup.moshi.Json
import java.util.*

data class ExampleData (
        val date: String,
        val cryptoCost: String,
        val currency: String
)