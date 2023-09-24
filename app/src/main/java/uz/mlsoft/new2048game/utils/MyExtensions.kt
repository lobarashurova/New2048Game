package uz.mlsoft.new2048game.utils

import android.graphics.Paint
import android.util.Log
import android.widget.TextView

fun TextView.underLine() {
    paint.flags = paint.flags or Paint.UNDERLINE_TEXT_FLAG
    paint.isAntiAlias = true
}

fun myLog(message: String) {
    Log.d("TTT", message)
}