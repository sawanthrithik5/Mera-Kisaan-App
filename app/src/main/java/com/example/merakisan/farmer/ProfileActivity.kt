package com.example.merakisan.farmer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.merakisan.R
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_company_profile.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileRef: DatabaseReference
    private lateinit var name: String
    private lateinit var number: String
    private lateinit var email: String
    private lateinit var imageUrl: String
    private lateinit var password: String
    private lateinit var ans1: String
    private lateinit var ans2: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profileRef = FirebaseDatabase.getInstance().getReference().child("Farmer")
        var name: String = intent.getStringExtra("name").toString()
        storeDataFromFirebase(name)
        profile_upload.setOnClickListener {
            uploadProfile()
        }
    }

    private fun uploadProfile() {
        var hm = hashMapOf<String, Any>(
            "Name" to profile_name.text.toString(),
            "PhoneNumber" to profile_number.text.toString(),
            "Email" to profile_email.text.toString(),
            "Password" to password,
            "ImageUri" to imageUrl
        )

        profileRef.child(name).setValue(hm).addOnCompleteListener {
            Toast.makeText(applicationContext, "Profile UPloded Successfully", Toast.LENGTH_SHORT).show()
        }
        var hm1 = hashMapOf<String, Any>(
            "Questoin1" to profile_question1.text.toString(),
            "Question2" to profile_question2.text.toString()
        )

        profileRef.child(name).child("Set Question").setValue(hm1).addOnCompleteListener {
            Toast.makeText(applicationContext, "Your Answer is updated", Toast.LENGTH_SHORT).show()
        }

    }

    private fun storeDataFromFirebase(name: String) {
        profileRef.child(name).addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    this@ProfileActivity.name = snapshot.child("Name").getValue().toString();
                    this@ProfileActivity.email = snapshot.child("Email").getValue().toString()
                    this@ProfileActivity.number = snapshot.child("PhoneNumber").getValue().toString()
                    this@ProfileActivity.imageUrl = snapshot.child("ImageUri").getValue().toString()
                    this@ProfileActivity.password = snapshot.child("Password").getValue().toString()
                    this@ProfileActivity.ans2 = snapshot.child("Set Question").child("Question2").getValue().toString()
                    this@ProfileActivity.ans1 = snapshot.child("Set Question").child("Questoin1").getValue().toString()
                    profile_question1.setText(ans1)
                    profile_question2.setText(ans2)
                    profile_name.setText(name)
                    profile_email.setText(email)
                    profile_number.setText(number)
                    Picasso.get().load(imageUrl).fit().into(profile_image)

                }else{
                    Toast.makeText(applicationContext, "Sorry for Inconvenience there is no such data", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}