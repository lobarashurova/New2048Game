package uz.mlsoft.new2048game.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

    companion object {
        private var myPref: SharedPref? = null

        public fun init(context: Context) {
            if (myPref == null) {
                myPref = SharedPref(context)
            }
        }

        fun getMyShared(): SharedPreferences {
            return myPref!!.sharedPreferences
        }
    }

}