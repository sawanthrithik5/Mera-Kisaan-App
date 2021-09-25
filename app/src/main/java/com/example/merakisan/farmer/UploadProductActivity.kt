package com.example.merakisan.farmer

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.merakisan.R
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_upload_product.*

class UploadProductActivity : AppCompatActivity() {
    private lateinit var root: FirebaseDatabase
    private lateinit var cropRef: DatabaseReference
    private lateinit var storageRef: StorageReference
    private var PICK_IMAGE = 486
    private var imageRef: Uri? = null
    private lateinit var link1: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_product)

        root = FirebaseDatabase.getInstance()
        cropRef = root.getReference().child("Crop")
        storageRef = FirebaseStorage.getInstance().reference.child("Crop Image")

        val uname = findViewById<EditText>(R.id.product_name)
        val udescription = findViewById<EditText>(R.id.product_discription)
        val uquantity = findViewById<EditText>(R.id.product_quantity)
        val uprice = findViewById<EditText>(R.id.product_price)

        product_image.setOnClickListener {
            uploadImageFromGallary()
        }
        product_upload.setOnClickListener {
            val pname = uname.text
            val pdiscription = udescription.text
            val pquantity = uquantity.text
            val pprice = uprice.text
            if(pname.toString().isEmpty() || pdiscription.toString().isEmpty()
                || pquantity.toString().isEmpty() || pprice.toString().isEmpty()){
                Toast.makeText(applicationContext, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
            uploadProductToFIrebase(pname.toString(), pdiscription.toString(), pquantity.toString(), pprice.toString())
        }
    }


    private fun uploadImageFromGallary() {
        var i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(i, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE && data != null && data.data != null){
            imageRef = data.data
            product_image.setImageURI(imageRef)
            storeProduct()
        }
    }

    private fun uploadProductToFIrebase(name: String, description: String, quantity: String, price: String){

        var hm = hashMapOf<String, Any>(
            "ProductName" to name,
            "Description" to description,
            "Quantity" to quantity,
            "Price" to price,
            "ProductImage" to link1
        )
        cropRef.child(name).setValue(hm).addOnCompleteListener {
            Toast.makeText(applicationContext, "Product uploaded Successfully", Toast.LENGTH_SHORT).show()
        }
    }
    private fun storeProduct() {
        if(imageRef != null){
            val fileRef = storageRef!!.child(System.currentTimeMillis().toString() + ".jpg")
            var uploadTask: StorageTask<*>
            uploadTask = fileRef.putFile(imageRef!!)
            uploadTask.continueWithTask (Continuation<UploadTask.TaskSnapshot, Task<Uri>>{ task ->
                if (!task.isSuccessful){
                    task.exception?.let { throw it }
                }
                return@Continuation fileRef.downloadUrl
            }).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val downloadImageUrl = task.result
                    val url = downloadImageUrl.toString()
                    link1 = url
                }
            }
        }
    }
}