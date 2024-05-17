package com.fedorkasper.kasper_chat_lite.tool

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import com.fedorkasper.kasper_chat_lite.activites.MainActivity
import java.io.IOException
import java.util.concurrent.Executors


class SocketService : Service() {
    private var isRunning = false

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isRunning) {
            isRunning = true
            Thread {
                try {

                    val activity = MainActivity.instance
                    val inputStream = activity.networkManager.socket.getInputStream()
                    val executors = Executors.newSingleThreadExecutor()
                    val handler = Handler(Looper.getMainLooper())

                    Log.d("Socket", "Получил входной стрим" )


                    executors.execute {
                        kotlin.run {
                            val buffer = ByteArray(1024)
                            var byte: Int
                            while (isRunning)  {
                                try {
                                    byte = inputStream.read(buffer)
                                    if (byte > 0) {
                                        val finalByte = byte
                                        handler.post {
                                            kotlin.run {
                                                val message = String(buffer, 0, finalByte).replace(0.toChar().toString(),"",false)
                                                activity.networkManager.recognize(message)
                                            }
                                        }

                                    }
                                } catch (ex: IOException) {
                                    Log.e("Socket", ex.message.toString())
                                }
                            }
                        }
                    }



                } catch (e: IOException) {
                    Log.d("Socket", "Service " + e.message.toString())
                }
            }.start()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false

        try {

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}