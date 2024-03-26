package com.chivers.kotlinwanandroid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chivers.kotlinwanandroid.repository.HomeFragmentRepository
import com.chivers.kotlinwanandroid.repository.HomeRepository

class HomeFragmentVMFactory(private  val repository: HomeFragmentRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(repository) as T
    }
}