package br.com.grupo4.classoverflow.core

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator


object Utils {

    const val SHORT_VIBRATE_LENGTH: Long = 50

    private var vibrator: Vibrator? = null

    fun vibrate(context: Context, millis: Long = SHORT_VIBRATE_LENGTH) {
        if(vibrator == null) vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        vibrator?.vibrate(VibrationEffect.createOneShot(millis, VibrationEffect.DEFAULT_AMPLITUDE))
    }
}