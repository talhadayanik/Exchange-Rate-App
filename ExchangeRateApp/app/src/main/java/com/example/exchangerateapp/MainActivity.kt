package com.example.exchangerateapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.Gravity
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var txtDate : TextView
    lateinit var btnCurrency : Button
    lateinit var txtUnit : TextView
    lateinit var txtDA : TextView
    lateinit var txtDS : TextView
    lateinit var txtEA : TextView
    lateinit var txtES : TextView
    var selectedCurrency : Currency? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val fetch = Fetch()
        val currencyList = fetch.fetchCurrencyData()
        val date = fetch.fetchDate()

        txtDate = findViewById(R.id.txtDate)
        btnCurrency = findViewById(R.id.btnCurrency)
        txtUnit = findViewById(R.id.txtUnit)
        txtDA = findViewById(R.id.txtDovizAlisValue)
        txtDS = findViewById(R.id.txtDovizSatisValue)
        txtEA = findViewById(R.id.txtEfektifAlisValue)
        txtES = findViewById(R.id.txtEfektifSatisValue)

        txtDate.text = date

        val currencyPopupMenu = PopupMenu(this, btnCurrency, Gravity.BOTTOM)
        for (item in currencyList){
            currencyPopupMenu.menu.add(0,currencyPopupMenu.menu.size(),0,"${item.Isim}")
        }

        btnCurrency.setOnClickListener {
            currencyPopupMenu.show()
            currencyPopupMenu.setOnMenuItemClickListener {
                selectedCurrency = currencyList.get(it.itemId)
                btnCurrency.text = selectedCurrency!!.Isim
                txtUnit.text = "${selectedCurrency!!.Unit} ${selectedCurrency!!.Kod}"
                txtDA.text = selectedCurrency!!.ForexBuying
                txtDS.text = selectedCurrency!!.ForexSelling
                txtEA.text = selectedCurrency!!.BanknoteBuying
                txtES.text = selectedCurrency!!.BanknoteSelling
                false
            }
        }

    }
}