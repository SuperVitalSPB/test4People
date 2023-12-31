package com.test.people.model

data class LatestRateEntity(
    val success: Boolean,
    val timestamp: Int,
    val base: String,
    val date: String,
    val rates: Map<String, Double>,
    )

