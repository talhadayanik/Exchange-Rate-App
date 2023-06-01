package com.example.exchangerateapp

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class Fetch {

    val url = "https://www.tcmb.gov.tr/kurlar/today.xml"
    val doc : Document = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get()

    fun fetchDate() : String {
        val element : Elements = doc.getElementsByTag("Tarih_Date")
        val date = element.attr("Tarih")
        return date
    }

    fun fetchCurrencyData() : List<Currency> {
        val list = mutableListOf<Currency>()
        val elements : Elements = doc.getElementsByTag("Currency")
        for(item in elements){
            if (item.getElementsByTag("Isim").text() != "ÖZEL ÇEKME HAKKI (SDR)"){
                val Unit = item.getElementsByTag("Unit").text()
                val Isim = item.getElementsByTag("Isim").text()
                val Kod = item.attr("Kod")
                val CurrencyName = item.getElementsByTag("CurrencyName").text()
                val ForexBuying = item.getElementsByTag("ForexBuying").text()
                val ForexSelling = item.getElementsByTag("ForexSelling").text()
                val BanknoteBuying = item.getElementsByTag("BanknoteBuying").text()
                val BanknoteSelling = item.getElementsByTag("BanknoteSelling").text()
                val CrossRateUSD = item.getElementsByTag("CrossRateUSD").text()
                val CrossRateOther = item.getElementsByTag("CrossRateOther").text()

                val currency = Currency(Unit, Isim, Kod, CurrencyName, ForexBuying, ForexSelling, BanknoteBuying, BanknoteSelling, CrossRateUSD, CrossRateOther)
                list.add(currency)
            }
        }
        return list
    }
}