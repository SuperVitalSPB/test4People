package com.test.people.model

import java.util.Random;

data class LatestRate(
    val success: Boolean? = null,
    val timestamp: Int? = null,
    val base: String? = null,
    val date: String? = null,
    val rates: List<Valute>? = null,
    ) {

    companion object {
        fun from(latestRateEntity: LatestRateEntity): LatestRate {
            return LatestRate(latestRateEntity.success,
                latestRateEntity.timestamp,
                latestRateEntity.base,
                latestRateEntity.date,
                mutableListOf<Valute>().apply {
                    latestRateEntity.rates
                        .toList()
                        .filter { valute ->
                            !valute.first.equals(latestRateEntity.base)
                        }
                        .map { item ->
                            this.add(Valute(item.first, item.second, false))
                        }
                })
        }
    }
}

