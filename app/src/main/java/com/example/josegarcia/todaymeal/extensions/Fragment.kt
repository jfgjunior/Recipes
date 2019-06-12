package com.example.josegarcia.todaymeal.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.josegarcia.todaymeal.ApplicationComponent

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> Fragment.viewModel(
    crossinline factory: () -> T
) = viewModels<T> {
    object : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>) = factory() as T
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> Fragment.sharedViewModel(
    crossinline factory: () -> T
) = activityViewModels<T> {
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>) = factory() as T
    }
}

val Fragment.inject: ApplicationComponent
    get() = requireActivity().application.inject