package com.example.merakisan.farmer

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.merakisan.FirstActivity
import com.example.merakisan.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var homeFgmt = HomeFragment()
        var orderFgmt = orderFragment()
        var trefrt = TrendingFragment()
        var name: String = intent.getStringExtra("name").toString()
        setCurrentFragment(homeFgmt)
        bottom_navigation_view.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.menu_home -> setCurrentFragment(homeFgmt)
                R.id.menu_work ->{
                    home_title.setText("Crop")
                    setCurrentFragment(orderFgmt)
                }
                R.id.menu_profile ->{
                    val i = Intent(this, ProfileActivity::class.java)
                    i.putExtra("name", name);
                    startActivity(i)
                }
                R.id.menu_add ->startActivity(Intent(this, UploadProductActivity::class.java))
            }
            true
        }
        home_logout.setOnClickListener{
            startActivity(Intent(applicationContext, FirstActivity::class.java))
            finish()
        }
        search_button.setOnClickListener {

        }
        home_trending.setOnClickListener {
            home_title.setText("Trending")
            setCurrentFragment(trefrt)
        }
    }

    override fun onStart() {
        super.onStart()
        home_title.setText("Home")
    }
    private fun setCurrentFragment(fragment: Fragment)=supportFragmentManager.beginTransaction().apply {
        replace(R.id.flFragment, fragment)
        commit()
    }
}