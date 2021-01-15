package com.articlesapp.features.articles

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.articlesapp.usecases.GetMostPopularArticlesUseCase
import com.articlesapp.usecases.articlesUseCase
import com.articlesapp.model.MostPopularArticlesResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class ArticlesViewModel (
    private val getMostPopularArticles: GetMostPopularArticlesUseCase = articlesUseCase

) : ViewModel(
) {
    val articlesResponse = PublishSubject.create<MostPopularArticlesResponse>()
    val articlesResponse1 = MutableLiveData<MostPopularArticlesResponse>()
    val isLoading = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()

     fun getArticles() {
          getMostPopularArticles.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
              .doOnSubscribe {
                  isLoading.value = true
              }
              .doFinally {
                  isLoading.value=false
              }
            .subscribe({
                it.let {
                    articlesResponse.onNext(it)
                    articlesResponse1.value=it
                    Log.e("success",it.status.toString())
                }
            }, {
                Log.e("error",it.message.toString())
            }).let {
                compositeDisposable.add(it)
            }
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }}