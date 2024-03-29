package com.chivers.kotlinwanandroid.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chivers.kotlinwanandroid.R
import com.chivers.kotlinwanandroid.model.Article
import com.facebook.drawee.view.SimpleDraweeView

class HomeArticlesAdapter : RecyclerView.Adapter<HomeArticlesAdapter.ArticleViewHolder>() {
    private var data = ArrayList<Article>()

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mTvAuthor = itemView.findViewById<TextView>(R.id.tvAuthor)
        private val mTvUplader = itemView.findViewById<TextView>(R.id.tvUploader)
        private val mIvProfile = itemView.findViewById<SimpleDraweeView>(R.id.ivProfileTop)
        fun bindData(article: Article){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false);
        val articleViewHolder = ArticleViewHolder(inflate);
        return articleViewHolder;
    }

    override fun getItemCount(): Int {
        return data.size;
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

    }
}