package com.example.securatum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnsignup: Button
    private lateinit var mDbRef:DatabaseReference

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Securatum)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()

        edtName = findViewById(R.id.editTextTextPersonName)
        edtEmail = findViewById(R.id.editTextTextEmailAddress2)
        edtPassword = findViewById(R.id.editTextTextPassword2)
        btnsignup = findViewById(R.id.button3)

        mAuth = FirebaseAuth.getInstance()

     btnsignup.setOnClickListener{
         val name = edtName.text.toString()
         val email = edtEmail.text.toString()
         val password = edtPassword.text.toString()

         signup(name,email,password)
     }
    }

    private fun signup(name:String,email:String,password:String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    val intent = Intent(this,User_list::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Some Error Occurred",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(name: String,email: String,uid:String){
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
    }
}
