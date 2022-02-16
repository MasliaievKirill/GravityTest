package com.masliaiev.gravitytest.di

import androidx.lifecycle.ViewModel
import com.masliaiev.gravitytest.presentation.view_models.LoadFragmentViewModel
import com.masliaiev.gravitytest.presentation.view_models.WebViewFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoadFragmentViewModel::class)
    fun bindLoadFragmentViewModel(impl: LoadFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WebViewFragmentViewModel::class)
    fun bindWebViewFragmentViewModel(impl: WebViewFragmentViewModel): ViewModel

}