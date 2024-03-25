package com.chivers.kotlinwanandroid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chivers.kotlinwanandroid.repository.MainRepository

class MainViewModelFactory(private  val repository: MainRepository)  :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}