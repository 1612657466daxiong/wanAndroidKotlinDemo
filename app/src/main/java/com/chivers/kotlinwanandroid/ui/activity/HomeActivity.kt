package com.chivers.kotlinwanandroid.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chivers.kotlinwanandroid.R
import com.chivers.kotlinwanandroid.databinding.ActivityHomeBinding
import com.chivers.kotlinwanandroid.repository.HomeRepository
import com.chivers.kotlinwanandroid.viewmodels.HomeViewModel
import com.chivers.kotlinwanandroid.viewmodels.HomeViewModelFactory

class HomeActivity : AppCompatActivity() {
    lateinit var dataBinding: ActivityHomeBinding
    lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        homeViewModel = ViewModelProvider(this,HomeViewModelFactory(HomeRepository())).get(HomeViewModel::class.java)
        homeViewModel.getBannerModel()
        homeViewModel.failed.observe(this){
            it->Toast.makeText(this,it,Toast.LENGTH_LONG)
        }

        homeViewModel.banner.observe(this) {
            it->dataBinding.viewmodel = homeViewModel
        }
    }
}