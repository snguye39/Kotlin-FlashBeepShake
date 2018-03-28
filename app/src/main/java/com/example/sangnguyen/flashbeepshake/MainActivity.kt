package com.example.sangnguyen.flashbeepshake

import android.content.Context
import android.graphics.Camera
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.ToggleButton

class MainActivity : AppCompatActivity() {

    lateinit var cameraManager: CameraManager
    private var cameraId: String? = null
    private var isOnOff: Button? = null
    private var isTorchOn: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isOnOff = findViewById<Button>(R.id.flashB) as Button
        isTorchOn = false
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        val shakeButton = findViewById<Button>(R.id.shakeB)
        shakeButton.setOnClickListener {
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(500)
        }

        val toneButton = findViewById<Button>(R.id.beepB)
        toneButton.setOnClickListener {
            val tone = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
            tone.startTone(ToneGenerator.TONE_DTMF_3, 600)
        }
    }

    fun flashPhone(view: View) {
        Log.i("Flash", "Flash Button pressed")
        cameraId = cameraManager!!.cameraIdList[0]
        Log.i("Flash", "Found ID")
        if (isTorchOn!!) {
            cameraManager!!.setTorchMode(cameraId!!, true)
            isTorchOn = false
        } else {
            cameraManager!!.setTorchMode(cameraId!!, false)
            isTorchOn = true
        }

    }
}






