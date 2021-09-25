package com.example.merakisan.company

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.merakisan.R
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_upload__requirements_.*

class UploadRequirementsActivity : AppCompatActivity() {

    private lateinit var root: FirebaseDatabase
    private lateinit var reqRef: DatabaseReference
    private lateinit var trendRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload__requirements_)

        root = FirebaseDatabase.getInstance()
        reqRef = root.getReference().child("Requirement")
        trendRef = root.getReference().child("Trending")

        val uname = findViewById<EditText>(R.id.req_crop_name)
        val ulastdate = findViewById<EditText>(R.id.req_crop_lastDate)
        val uquantity = findViewById<EditText>(R.id.req_crop_quantity)
        val uprice = findViewById<EditText>(R.id.req_crop_price)

        req_crop_upload.setOnClickListener {
            var name = uname.text
            var lastdate = ulastdate.text
            var quantity = uquantity.text
            var price = uprice.text
            if(uname.toString().isEmpty() || lastdate.toString().isEmpty()
                || quantity.toString().isEmpty() || price.toString().isEmpty()){
                Toast.makeText(applicationContext, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }else{
                uploadRequirements(name.toString(), lastdate.toString(), quantity.toString(), price.toString())
            }
        }

    }

    private fun uploadRequirements(name: String, lastdate: String, quantity: String, price: String) {
            var hm = hashMapOf<String, Any>(
                "CropName" to name,
                "LastData" to lastdate,
                "Quantity" to quantity,
                "Price" to price
            )

            trendRef.child(name).addValueEventListener(object: ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        var a: Int = snapshot.child("Rate").getValue() as Int
                        var b = a + 1
                        var c: String = b.toString()
                        trendRef.child(name).child("Rate").setValue(c)
                    }else{
                        var hm1 = hashMapOf<String, Any>(
                            "CropName" to name,
                            "Rate" to "1"
                        )
                        trendRef.child(name).setValue(hm1)
                    }

                }

            })
            reqRef.child(name).setValue(hm).addOnCompleteListener {
                Toast.makeText(applicationContext, "Requirement Uploaded Successfully", Toast.LENGTH_SHORT).show()
            }

    }


}