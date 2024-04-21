package com.chivers.kotlinwanandroid.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.chivers.kotlinwanandroid.databinding.FragmentHomeBinding
import com.chivers.kotlinwanandroid.repository.HomeFragmentRepository
import com.chivers.kotlinwanandroid.repository.HomeRepository
import com.chivers.kotlinwanandroid.ui.adapter.HomeArticlesAdapter
import com.chivers.kotlinwanandroid.ui.adapter.HomeBannerAdapter
import com.chivers.kotlinwanandroid.viewmodels.HomeFragmentVMFactory
import com.chivers.kotlinwanandroid.viewmodels.HomeFragmentViewModel
import com.chivers.kotlinwanandroid.viewmodels.HomeViewModel
import com.chivers.kotlinwanandroid.viewmodels.HomeViewModelFactory


class HomeFragment : Fragment()  {

    private var _binding: FragmentHomeBinding? = null
    private var adapter:HomeBannerAdapter? =null
    private var articleAdapter:HomeArticlesAdapter? =null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private  var homeViewModel: HomeFragmentViewModel? =null
    private var changeCallback:OnPageChangeCallback?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        adapter  =  HomeBannerAdapter()
        articleAdapter = HomeArticlesAdapter()
        binding.homeBanner.adapter = adapter
        binding.homeList.adapter = articleAdapter
        binding.homeList.layoutManager = LinearLayoutManager(this.context)


        return root
    }



    override fun onStart() {
        super.onStart()
        homeViewModel = ViewModelProvider(this, HomeFragmentVMFactory(HomeFragmentRepository())).get(HomeFragmentViewModel::class.java)
        homeViewModel?.getBannerModel()
        homeViewModel?.failed?.observe(this){
                it->
            Toast.makeText(context,it, Toast.LENGTH_LONG)
        }

        homeViewModel?.banner?.observe(this) {
                it->binding?.viewmodel = homeViewModel
            adapter?.setData(binding.viewmodel?.banner?.value?.data)
        }
        homeViewModel?.getArticleModel()

        homeViewModel?.articles?.observe(this){
            it->binding?.viewmodel = homeViewModel
            articleAdapter?.setData(binding.viewmodel?.articles?.value?.data?.datas)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}