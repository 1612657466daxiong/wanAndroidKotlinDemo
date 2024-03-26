package com.chivers.kotlinwanandroid.ui.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chivers.kotlinwanandroid.R
import com.chivers.kotlinwanandroid.model.Banner

class HomeBannerAdapter :RecyclerView.Adapter<HomeBannerAdapter.BannerViewHolder>() {
    private var mList:List<Banner>? = ArrayList();

    fun setData(list:List<Banner>?){
        Log.i("xxxxxx",list!!.toString())
        this.mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_banner,parent,false);
        return BannerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if(mList?.size!!>0) mList!!.size else 0
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bindData(mList?.get(position))
    }


    class BannerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private val mTvBanner = itemView.findViewById<TextView>(R.id.tv_banner)
        private val mIvBanner = itemView.findViewById<ImageView>(R.id.iv_banner)
        fun bindData(banner:Banner?){
            val uri = Uri.parse(banner?.imagePath)
            mTvBanner.text = banner?.title
            mIvBanner.setImageURI(uri)
        }
    }
}