package com.articlesapp.features.articles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.articlesapp.R
import com.articlesapp.model.Result
import com.squareup.picasso.Picasso

class ArticlesAdapter (
    private val articlesResult: List<Result>,
    private val navController: NavController
) : RecyclerView.Adapter<ArticlesViewHolder>() {

    override fun onCreateViewHolder(parentView: ViewGroup, p1: Int): ArticlesViewHolder {
        return LayoutInflater
            .from(parentView.context)
            .inflate(R.layout.articles_list_item, parentView, false)
            .let { ArticlesViewHolder(it) }
    }

    override fun onBindViewHolder(viewHolder: ArticlesViewHolder, position: Int) {
        viewHolder.bind(articlesResult[position])
        viewHolder.itemView.setOnClickListener {
            if (navController.currentDestination?.id == R.id.articlesFragment)
                navController.navigate(
                    ArticlesFragmentDirections.actionArticlesFragmentToArticlesDetailsFragment(
                        articlesResult[position]
                    )
                )
        }
    }

    override fun getItemCount() = articlesResult.size

}

class ArticlesViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {

    private val tvTitle by lazy { view.findViewById<TextView>(R.id.tv_title) }
    private val ivArticles by lazy { view.findViewById<ImageView>(R.id.iv_article) }

    fun bind(article: Result) {
        tvTitle.text = article.title ?: ""
            val posterImage =
                article.media?.get(0)?.mediaMetadata?.get(1)?.url
            Picasso.with(itemView.context).load(posterImage)
                .into(ivArticles)
        }
    }


