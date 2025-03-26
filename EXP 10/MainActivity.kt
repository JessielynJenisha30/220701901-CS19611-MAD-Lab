package com.example.phonedetails

import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val REQUEST_PERMISSION_CODE = 301
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_PHONE_STATE), REQUEST_PERMISSION_CODE)
        }

        val etNwOpName : EditText = findViewById(R.id.etNwOpName)
        val etCountryISO : EditText = findViewById(R.id.etCountryISO)
        val etPhType : EditText = findViewById(R.id.etPhType)
        val etSIMState : EditText = findViewById(R.id.etSIMState)
        val etNwType : EditText = findViewById(R.id.etNwType)

        val btService : Button = findViewById(R.id.btService)

        val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

        btService.setOnClickListener{
            etNwOpName.setText(telephonyManager.networkOperatorName)
            etCountryISO.setText(telephonyManager.networkCountryIso)
            val phoneType = when(telephonyManager.phoneType){
                TelephonyManager.PHONE_TYPE_CDMA -> "CDMA"
                TelephonyManager.PHONE_TYPE_GSM -> "GSM"
                else -> "Others"
            }
            etPhType.setText(phoneType)
            val simState = when(telephonyManager.simState){
                TelephonyManager.SIM_STATE_ABSENT -> "Absent"
                TelephonyManager.SIM_STATE_READY -> "Ready"
                else -> "Others"
            }
            etSIMState.setText(simState)
            val networkType = when(telephonyManager.networkType){
                TelephonyManager.NETWORK_TYPE_LTE -> "43"
                TelephonyManager.NETWORK_TYPE_NR -> "58"
                else -> "Others"
            }
            etNwType.setText(networkType)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
