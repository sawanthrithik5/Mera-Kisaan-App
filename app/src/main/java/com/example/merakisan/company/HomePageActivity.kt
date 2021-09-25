package com.example.merakisan.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.merakisan.FirstActivity
import com.example.merakisan.R
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val homeFragment = ComapnyHomeFragment()
        val availableCropList = CompanyCropFragment()

        var name1: String = intent.getStringExtra("name").toString()
        setCurrentFragment(homeFragment)

        bottom_navigation_view_page.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home_page -> setCurrentFragment(homeFragment)
                R.id.add_requeirment ->startActivity(Intent(this, UploadRequirementsActivity::class.java))
                R.id.company_profile ->{
                    val i = Intent(this, CompanyProfileActivity::class.java)
                    i.putExtra("name", name1)
                    startActivity(i)
                }
                R.id.crop_details -> setCurrentFragment(availableCropList)
            }
            true
        }
        home_page_logout.setOnClickListener{
            startActivity(Intent(applicationContext, FirstActivity::class.java))
            finish()
        }
        search_page_button.setOnClickListener {

        }
        home_page_trending.setOnClickListener {
            startActivity(Intent(applicationContext, CompanyTrendingActivity::class.java))
        }


    }

    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.flFragment_page,fragment)
        commit()
    }
}