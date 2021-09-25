package com.example.merakisan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.merakisan.company.CompanyLoginActivity
import com.example.merakisan.company.HomePageActivity
import com.example.merakisan.farmer.LoginActivity
import com.example.merakisan.farmer.RegisterActivity
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {
    private var backPressedTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        // IN kotlin we can declare variable using (val) keyword
        // IN kotlin we can directly use key of xml file
        login_user.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // here register_user is a id of button i am using directly
        register_user.setOnClickListener {
            // we don't have to write interface called onClickListener
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        company.setOnClickListener {
            val i =Intent(applicationContext, CompanyLoginActivity::class.java)
            startActivity(i)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}