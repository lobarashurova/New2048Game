package uz.mlsoft.new2048game.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import uz.mlsoft.new2048game.data.local.SideEnum
import kotlin.math.abs

class MyTouchListener(private val context: Context) : View.OnTouchListener {
    private val myGesture = GestureDetector(context, MyGesture())
    private var detectSideListener: ((SideEnum) -> Unit)? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        myGesture.onTouchEvent(event)
        return true
    }

    inner class MyGesture : SimpleOnGestureListener() {
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (e1 != null) {
                if (abs(e1.x - e2.x) > 100 || abs(e1.y - e2.y) > 100) {
                    if (abs(e1.x - e2.x) > abs(e1.y - e2.y)) {
                        if (e1.x > e2.x) {
                            detectSideListener?.invoke(SideEnum.LEFT)
                            // left
                        } else {
                            detectSideListener?.invoke(SideEnum.RIGHT)
                            // right
                        }
                    } else {
                        if (e1.y > e2.y) {
                            // up
                            detectSideListener?.invoke(SideEnum.UP)
                        } else {
                            // down
                            detectSideListener?.invoke(SideEnum.DOWN)
                        }
                        // vertical
                    }
                }
            }
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }

    fun setDetectSideListener(block: (SideEnum) -> Unit) {
        detectSideListener = block
    }
}


