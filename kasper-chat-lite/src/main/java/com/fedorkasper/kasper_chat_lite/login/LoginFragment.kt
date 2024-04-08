package com.fedorkasper.kasper_chat_lite.login

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.fedorkasper.kasper_chat_lite.DataModel
import com.fedorkasper.kasper_chat_lite.R
import com.fedorkasper.kasper_chat_lite.databinding.FragmentLoginBinding
import com.fedorkasper.kasper_chat_lite.network.apiManager


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val dataModel:DataModel by activityViewModels()
    companion object {
        const val NOTIFICATION_ID = 101
        const val CHANNEL_ID = "KasperChat"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registrationButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)

            // Создаём уведомление
            val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Пора покормить кота")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val notificationManager = NotificationManagerCompat.from(requireContext())
            notificationManager.notify(NOTIFICATION_ID, builder.build())
        }

        binding.confirmButton.setOnClickListener {
            with(binding){

                    apiManager.API_LOGIN(editTextNameUser.text.toString(),editTextPassword.text.toString())

            }
        }
    }


}