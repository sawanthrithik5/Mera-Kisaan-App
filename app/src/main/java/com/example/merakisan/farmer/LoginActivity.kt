package com.example.merakisan.farmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.merakisan.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var root: FirebaseDatabase
    private lateinit var farmerRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        root = FirebaseDatabase.getInstance()
        farmerRef = root.getReference("Farmer")

        login_button.setOnClickListener {
            //Login Button is used to move to homePage
            if(login_Username.text.toString().trim().isEmpty() || login_password.text.toString().trim().isEmpty()){
                Toast.makeText(applicationContext, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }else {
                checkUserPresent(login_Username.text.toString().trim(), login_password.text.toString().trim())
            }
        }
        login_NotAUser.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)

        }
    }

    private fun checkUserPresent(username:String, password:String) {
        farmerRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.child(username).exists()){
                    var pass = snapshot.child(username).child("Password").value.toString();
                    if(pass.equals(password)){
                        Toast.makeText(applicationContext,"You are Welcome", Toast.LENGTH_SHORT).show()
                        val i = Intent(applicationContext, HomeActivity::class.java)
                        i.putExtra("name", login_Username.text.toString())
                        startActivity(i)
                    }else{
                        Toast.makeText(applicationContext,"Password not matched", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(applicationContext, "Please Register before login", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,"Sorry Database is Error please try again", Toast.LENGTH_SHORT).show()
            }

        })

    }
}