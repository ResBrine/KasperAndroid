package com.example.testpoject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testpoject.databinding.LayoutLoginBinding
import com.example.testpoject.databinding.LayoutRegistrationBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var bindingLogin:LayoutLoginBinding
    private lateinit var bindingRegistration: LayoutRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLogin = LayoutLoginBinding.inflate(layoutInflater)
        bindingRegistration = LayoutRegistrationBinding.inflate(layoutInflater)
        setContentView(bindingLogin.root)
        bindingLogin.textViewRegistration.setOnClickListener {
            setContentView(bindingRegistration.root)
        }
        bindingRegistration.textViewLogin.setOnClickListener {
            setContentView(bindingLogin.root)
        }
    }
}