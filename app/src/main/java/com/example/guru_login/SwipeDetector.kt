package com.example.guru_login

import android.util.Log
import android.view.MotionEvent
import android.view.View

class SwipeDetector : View.OnTouchListener {
    val HORIZONTAL_MIN_DISTANCE = 60
    val VERTICAL_MIN_DISTANCE = 80
    private var downX: Float = 0.toFloat()
    private var downY: Float = 0.toFloat()
    private var upX: Float = 0.toFloat()
    private var upY: Float = 0.toFloat()
    var action = Action.None
        private set

    enum class Action {
        LR, // Left to Right
        RL, // Right to Left
        TB, // Top to bottom
        BT, // Bottom to Top
        None // when no action was detected
    }

    fun swipeDetected(): Boolean {
        return action != Action.None
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                action = Action.None
                return false // allow other events like Click to be processed
            }
            MotionEvent.ACTION_MOVE -> {
                upX = event.x
                upY = event.y

                val deltaX = downX - upX
                val deltaY = downY - upY

                // horizontal swipe detection
                if (Math.abs(deltaX) > HORIZONTAL_MIN_DISTANCE) {
                    // left or right
                    if (deltaX < 0) {
                        Log.i(logTag, "Swipe Left to Right")
                        action = Action.LR
                        return true
                    }
                    if (deltaX > 0) {
                        Log.i(logTag, "Swipe Right to Left")
                        action = Action.RL
                        return true
                    }
                } else
                // vertical swipe detection
                    if (Math.abs(deltaY) > VERTICAL_MIN_DISTANCE) {
                        // top or down
                        if (deltaY < 0) {
                            Log.i(logTag, "Swipe Top to Bottom")
                            action = Action.TB
                            return false
                        }
                        if (deltaY > 0) {
                            Log.i(logTag, "Swipe Bottom to Top")
                            action = Action.BT
                            return false
                        }
                    }
                return true
            }
        }
        return false
    }

    companion object {

        private val logTag = "SwipeDetector"
        private val MIN_DISTANCE = 100
    }
}