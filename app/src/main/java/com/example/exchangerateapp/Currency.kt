package com.example.exchangerateapp

data class Currency(
    val Unit : String,
    val Isim : String,
    val Kod : String,
    val CurrencyName : String,
    val ForexBuying : String,
    val ForexSelling : String,
    val BanknoteBuying : String,
    val BanknoteSelling : String,
    val CrossRateUSD : String?,
    val CrossRateOther: String?
)