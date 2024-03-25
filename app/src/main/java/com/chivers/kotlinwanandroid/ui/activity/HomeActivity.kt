package com.chivers.kotlinwanandroid.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chivers.kotlinwanandroid.R
import com.chivers.kotlinwanandroid.TestXX.APIClient
import com.chivers.kotlinwanandroid.TestXX.LoginRegisterResponse
import com.chivers.kotlinwanandroid.TestXX.LoginRegisterResponseWrapper
import com.chivers.kotlinwanandroid.TestXX.WanAndroidAPI
import com.chivers.kotlinwanandroid.databinding.ActivityHomeBinding
import com.chivers.kotlinwanandroid.repository.HomeRepository
import com.chivers.kotlinwanandroid.viewmodels.HomeViewModel
import com.chivers.kotlinwanandroid.viewmodels.HomeViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {
    private  var dataBinding: ActivityHomeBinding? =null
    private  var homeViewModel: HomeViewModel? =null

    private lateinit var tv:TextView
    private lateinit var btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        homeViewModel = ViewModelProvider(this,HomeViewModelFactory(HomeRepository())).get(HomeViewModel::class.java)
        homeViewModel?.getBannerModel()
        homeViewModel?.failed?.observe(this){
            it->Toast.makeText(this,it,Toast.LENGTH_LONG)
        }

        homeViewModel?.banner?.observe(this) {
            it->dataBinding?.viewmodel = homeViewModel
        }
        tv = findViewById(R.id.tv)
        btn = findViewById(R.id.btn)
        btn.setOnClickListener(View.OnClickListener {
            GlobalScope.launch (Dispatchers.IO){
                val result:LoginRegisterResponseWrapper<LoginRegisterResponse> = APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                    .loginAction("Chivers","123456")
                tv.setText(result?.data.toString())
            }
        })
    }


}