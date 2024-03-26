package com.chivers.kotlinwanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chivers.kotlinwanandroid.databinding.FragmentHomeBinding
import com.chivers.kotlinwanandroid.repository.HomeFragmentRepository
import com.chivers.kotlinwanandroid.repository.HomeRepository
import com.chivers.kotlinwanandroid.ui.adapter.HomeBannerAdapter
import com.chivers.kotlinwanandroid.viewmodels.HomeFragmentVMFactory
import com.chivers.kotlinwanandroid.viewmodels.HomeFragmentViewModel
import com.chivers.kotlinwanandroid.viewmodels.HomeViewModel
import com.chivers.kotlinwanandroid.viewmodels.HomeViewModelFactory


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var adapter:HomeBannerAdapter? =null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private  var homeViewModel: HomeFragmentViewModel? =null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        adapter  =  HomeBannerAdapter()
        binding.homeBanner.adapter = adapter
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}