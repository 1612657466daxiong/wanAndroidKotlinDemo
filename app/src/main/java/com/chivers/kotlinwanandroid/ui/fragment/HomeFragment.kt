package com.chivers.kotlinwanandroid.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.chivers.kotlinwanandroid.databinding.FragmentHomeBinding
import com.chivers.kotlinwanandroid.livedatabus.OKLiveDataBusKT
import com.chivers.kotlinwanandroid.repository.HomeFragmentRepository
import com.chivers.kotlinwanandroid.repository.HomeRepository
import com.chivers.kotlinwanandroid.ui.adapter.HomeArticlesAdapter
import com.chivers.kotlinwanandroid.ui.adapter.HomeBannerAdapter
import com.chivers.kotlinwanandroid.ui.base.BaseFragment
import com.chivers.kotlinwanandroid.ui.view.ArtiDecoration
import com.chivers.kotlinwanandroid.viewmodels.HomeFragmentVMFactory
import com.chivers.kotlinwanandroid.viewmodels.HomeFragmentViewModel
import com.chivers.kotlinwanandroid.viewmodels.HomeViewModel
import com.chivers.kotlinwanandroid.viewmodels.HomeViewModelFactory
import com.scwang.smart.refresh.layout.listener.OnRefreshListener


class HomeFragment : BaseFragment()  {

    private var _binding: FragmentHomeBinding? = null
    private var adapter:HomeBannerAdapter? =null
    private var articleAdapter:HomeArticlesAdapter? =null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private  var homeViewModel: HomeFragmentViewModel? =null
    private var changeCallback:OnPageChangeCallback?=null
    private var pageIndex =1
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
        binding.homeList.addItemDecoration(ArtiDecoration(10))
        binding.homeList.layoutManager = LinearLayoutManager(this.context)
        createView();
        return root
    }

    private fun createView() {
        binding.smartRefreshLayout.setOnRefreshListener{
            pageIndex=1;
            Log.e("change", "============================OnRefresh \n $it")
            homeViewModel?.getArticleModel(pageIndex)

        }
        binding.smartRefreshLayout.setOnLoadMoreListener{
            Log.e("change", "============================LoadMore \n $it")
            pageIndex +=1
            homeViewModel?.getArticleModel(pageIndex)
        }
    }


    override fun onStart() {
        super.onStart()
        homeViewModel = ViewModelProvider(this, HomeFragmentVMFactory(HomeFragmentRepository())).get(HomeFragmentViewModel::class.java)
        homeViewModel?.getBannerModel()
        homeViewModel?.homeRepository1?.failed?.observe(this){
                it->
            Toast.makeText(context,it, Toast.LENGTH_LONG)
        }

        homeViewModel?.homeRepository1?.bannerImage?.observe(this) {
                it->binding?.viewmodel = homeViewModel
            adapter?.setData(it?.data)
        }
        homeViewModel?.getArticleModel(pageIndex)

        homeViewModel?.homeRepository1?.articlesList?.observe(this){
            it->binding?.viewmodel = homeViewModel
            Log.e("change", "==================================== \n $it")
            if(pageIndex==1){
                articleAdapter?.setData(it)
                binding?.smartRefreshLayout?.finishRefresh(true)
            }else{
                articleAdapter?.addData(it)
                binding?.smartRefreshLayout?.finishLoadMore(true)
            }

        }
        homeViewModel?.homeRepository1?.failedArticle?.observe(this){

            if(pageIndex==1){
                binding?.smartRefreshLayout?.finishRefresh(false)
            }else{
                binding?.smartRefreshLayout?.finishLoadMore(false)
            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}