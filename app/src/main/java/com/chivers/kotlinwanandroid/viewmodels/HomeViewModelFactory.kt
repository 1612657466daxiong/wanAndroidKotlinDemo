package com.chivers.kotlinwanandroid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chivers.kotlinwanandroid.repository.BaseRepository
import com.chivers.kotlinwanandroid.repository.HomeRepository

class HomeViewModelFactory(private  val repository: HomeRepository) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}