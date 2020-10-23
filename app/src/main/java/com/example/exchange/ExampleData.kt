package com.example.exchange

import com.squareup.moshi.Json
import java.util.*

class ExampleData (
        val date: String,
        @Json(name="other_format_field_name") val cryptoCost: String,
        val currency: String
)