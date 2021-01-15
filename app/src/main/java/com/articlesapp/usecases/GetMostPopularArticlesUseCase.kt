package com.articlesapp.usecases

import com.articlesapp.model.MostPopularArticlesResponse
import com.articlesapp.repositories.ArticlesRepository
import com.articlesapp.repositories.articlesRepository
import io.reactivex.Single

val articlesUseCase: GetMostPopularArticlesUseCase by lazy { GetMostPopularArticlesUseCase() }
class GetMostPopularArticlesUseCase(
    private val repository: ArticlesRepository= articlesRepository
)  {

    operator fun invoke(): Single<MostPopularArticlesResponse> {
       return repository.getMostPopularArticles()
    }

}