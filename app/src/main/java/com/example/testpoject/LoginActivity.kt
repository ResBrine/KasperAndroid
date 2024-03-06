package com.example.testpoject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.example.testpoject.constants.AndroidUtils
import com.example.testpoject.databinding.LayoutLoginBinding
import com.example.testpoject.databinding.LayoutRegistrationBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var bindingLogin:LayoutLoginBinding
    private lateinit var bindingRegistration: LayoutRegistrationBinding

    private var isRegistration = false// as viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.println(Log.INFO,"my_log","onCrate")

        bindingLogin = LayoutLoginBinding.inflate(layoutInflater)
        bindingRegistration = LayoutRegistrationBinding.inflate(layoutInflater)
        setContentView( if(isRegistration) bindingRegistration.root else bindingLogin.root)
        bindingLogin.textViewRegistration.setOnClickListener {
            setContentView(bindingRegistration.root)
            isRegistration = true
        }
        bindingRegistration.textViewLogin.setOnClickListener {
            setContentView(bindingLogin.root)
            isRegistration = false
        }
        bindingLogin.root.setOnClickListener {
            AndroidUtils.hideKeyboard(it)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.println(Log.INFO,"my_log","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.println(Log.INFO,"my_log","onResume")
    }
}