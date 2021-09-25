package com.example.merakisan.farmer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.merakisan.R
import com.google.android.gms.tasks.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {
    private lateinit var root: FirebaseDatabase
    private lateinit var farmerRef: DatabaseReference
    private var storageRef: StorageReference? = null
    private var imageUrl: Uri? = null
    private var PICK_IMAGE: Int = 438
    private lateinit var link1: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        root = FirebaseDatabase.getInstance()
        farmerRef = root.getReference("Farmer")
        storageRef = FirebaseStorage.getInstance().reference.child("Image")

        val rname = findViewById<EditText>(R.id.register_name)
        val remail = findViewById<EditText>(R.id.register_email)
        val rpassword = findViewById<EditText>(R.id.register_password)
        val rcpassword = findViewById<EditText>(R.id.register_confirm_password)

        register_profile.setOnClickListener {
            openGallery()
        }

        val name = rname.text
        var email = register_email.text
        var password = register_password.text
        var confirmPassword = register_confirm_password.text

        register_button.setOnClickListener {
            if (name.toString().isEmpty() || email.toString().isEmpty() || password.toString()
                    .isEmpty() || confirmPassword.toString().isEmpty()
            ) {
                Toast.makeText(applicationContext, "Please fill all the fields", Toast.LENGTH_SHORT)
                    .show()
            } else if (!(password.toString()).equals(confirmPassword.toString())) {
                Toast.makeText(applicationContext, "Password doesn't matched", Toast.LENGTH_SHORT)
                    .show()
            } else {
                uploadToFirebase(name.toString(), email.toString(), password.toString())
            }
        }
        //checkData();
        register_NotAUser.setOnClickListener {
            val i = Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)
        }

    }

    private fun openGallery() {
        var i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(i, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE && data != null && data.data != null) {
            imageUrl = data.data!!
            register_profile.setImageURI(imageUrl)
            storeProdcut()
        } else {
            Toast.makeText(applicationContext, "Data is null", Toast.LENGTH_SHORT).show()
        }
    }

    //request.auth != null
    private fun uploadToFirebase(name: String, email: String, password: String) {
        var hm = hashMapOf<String, Any>(
            "Name" to name,
            "Email" to email,
            "Password" to password,
            "PhoneNumber" to register_phone.text.toString(),
            "ImageUri" to link1
        )

        farmerRef.child(name).setValue(hm).addOnCompleteListener {
            Toast.makeText(applicationContext, "Registeration is Successfull !", Toast.LENGTH_SHORT)
                .show()
            val i = Intent(applicationContext, HomeActivity::class.java)
            i.putExtra("name", name)
            startActivity(i)
        }
    }

    private fun storeProdcut() {
        if (imageUrl != null){
            val fileRef = storageRef!!.child(System.currentTimeMillis().toString() + ".jpg")
            var uploadTask: StorageTask<*>
            uploadTask = fileRef.putFile(imageUrl!!)
            uploadTask.continueWithTask(Continuation <UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if(!task.isSuccessful){
                    task.exception?.let { throw  it }
                }
                return@Continuation fileRef.downloadUrl
            }).addOnCompleteListener { task ->
                if (task.isSuccessful){
                   val downloadImageurl = task.result
                    val url = downloadImageurl.toString()
                    link1 = url;
                }
            }
        }
    }
}