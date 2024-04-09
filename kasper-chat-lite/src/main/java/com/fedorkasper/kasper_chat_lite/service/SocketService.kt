package com.fedorkasper.kasper_chat_lite.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.fedorkasper.kasper_chat_lite.network.networkManager
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.ServerSocket
class SocketService : Service() {
    private var serverSocket: ServerSocket? = null
    private var isRunning = false

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isRunning) {
            isRunning = true
            Thread {
                try {
                    while (isRunning) {
                        val input = BufferedReader(InputStreamReader(networkManager.socket.getInputStream()))
                        val message = input.readLine() // Получение сообщения от клиента
                        Log.d("Socket", message)
                        
                        // Обработка сообщения здесь
                        input.close()
                        networkManager.socket.close()
                    }
                } catch (e: IOException) {
                    Log.d("Socket", e.message.toString())
                }
            }.start()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        try {
            serverSocket?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}