package com.chivers.kotlinwanandroid.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.chivers.kotlinwanandroid.R
import com.chivers.kotlinwanandroid.databinding.ActivityMainBinding
import com.chivers.kotlinwanandroid.repository.HomeRepository
import com.chivers.kotlinwanandroid.repository.MainRepository
import com.chivers.kotlinwanandroid.viewmodels.HomeViewModel
import com.chivers.kotlinwanandroid.viewmodels.HomeViewModelFactory
import com.chivers.kotlinwanandroid.viewmodels.MainViewModel
import com.chivers.kotlinwanandroid.viewmodels.MainViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private  var mainViewModel: MainViewModel? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setSupportActionBar(binding.appBarMain.toolbar)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository())).get(MainViewModel::class.java)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: BottomNavigationView = binding.appBarMain.contentMain.bottomNavi
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val view: View = binding.navView.getHeaderView(0)
        val tvName =view.findViewById<TextView>(R.id.tv_name)
       mainViewModel?.mainViewModel?.userInfo?.observe(this){
           tvName.setText(it?.data?.userInfo?.nickname)
           binding.navView.getHeaderView(0).findViewById<TextView>(R.id.tv_email).setText(it.data.userInfo.email)
        }
        mainViewModel?.mainViewModel?.getUserInfoRequest()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}