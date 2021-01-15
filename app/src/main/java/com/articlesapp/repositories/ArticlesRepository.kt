package com.articlesapp.repositories

import com.articlesapp.model.MostPopularArticlesResponse
import com.articlesapp.network.ArticlesApis
import com.articlesapp.network.articlesApis
import io.reactivex.Single


val articlesRepository: ArticlesRepository by lazy { ArticlesRepositoryImpl() }
interface ArticlesRepository {
    fun getMostPopularArticles(): Single<MostPopularArticlesResponse>
}
class ArticlesRepositoryImpl (
    private val server: ArticlesApis =articlesApis
) : ArticlesRepository {
    override fun getMostPopularArticles(): Single<MostPopularArticlesResponse> {
        return server.getMostPopularArticles()
    }
}