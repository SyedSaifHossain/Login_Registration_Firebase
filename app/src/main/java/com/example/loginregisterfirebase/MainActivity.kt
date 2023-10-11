package com.example.loginregisterfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginregisterfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.registerBtn.setOnClickListener{

            if(binding.emailText.text.toString() == " " || binding.passText.text.toString() == " "){

                Toast.makeText(this@MainActivity,"Enter all info", Toast.LENGTH_SHORT).show()
            }
            else{
                Firebase.auth.createUserWithEmailAndPassword(
                    binding.emailText.text.toString(),
                    binding.passText.text.toString()
                ).addOnCompleteListener{
                if (it.isSuccessful){
//                    binding.emailText.text?.clear()
//                    binding.passText.text?.clear()
                    startActivity(Intent(this@MainActivity,HomeActivity::class.java))
                    finish()
                    Toast.makeText(this@MainActivity,"Registered",Toast.LENGTH_SHORT).show()
                }
                    else{
                    Toast.makeText(this@MainActivity,it.exception?.localizedMessage,Toast.LENGTH_SHORT).show()
                }

                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(Firebase.auth.currentUser!=null){
            startActivity(Intent(this@MainActivity,HomeActivity::class.java))
            finish()

        }

    }
}