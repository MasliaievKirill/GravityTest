package com.masliaiev.gravitytest.di

import android.app.Application
import com.masliaiev.gravitytest.presentation.fragments.LoadFragment
import com.masliaiev.gravitytest.presentation.fragments.WebViewFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(fragment: LoadFragment)

    fun inject(fragment: WebViewFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}