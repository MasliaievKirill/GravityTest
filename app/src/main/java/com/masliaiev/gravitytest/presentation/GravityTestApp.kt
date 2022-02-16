package com.masliaiev.gravitytest.presentation

import android.app.Application
import com.masliaiev.gravitytest.di.DaggerApplicationComponent

class GravityTestApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}