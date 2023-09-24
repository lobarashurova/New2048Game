package uz.mlsoft.new2048game.app

import android.app.Application
import uz.mlsoft.new2048game.data.local.SharedPref

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}