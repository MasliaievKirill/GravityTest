package com.masliaiev.gravitytest.di

import android.app.Application
import com.masliaiev.gravitytest.presentation.LoadFragment
import com.masliaiev.gravitytest.presentation.WebViewFragment
import dagger.BindsInstance
import dagger.Component

@Component
interface ApplicationComponent {

    fun inject(fragment: LoadFragment)

    fun inject(fragment: WebViewFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}