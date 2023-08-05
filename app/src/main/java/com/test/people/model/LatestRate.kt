package com.test.people.model

data class LatestRate(
    val success: Boolean,
    val timestamp: Int,
    val base: String,
    val date: String,
    val rates: List<Valute>,
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

