package com.articlesapp.features.articles


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.articlesapp.model.MostPopularArticlesResponse
import com.articlesapp.model.Result
import com.articlesapp.network.ArticlesApis
import com.articlesapp.network.articlesApis
import com.articlesapp.repositories.ArticlesRepositoryImpl
import com.articlesapp.repositories.articlesRepository
import com.articlesapp.usecases.GetMostPopularArticlesUseCase
import com.articlesapp.usecases.articlesUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.Callable


@RunWith(JUnit4::class)
class ArticlesViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    var useCase = articlesUseCase

    lateinit var articlesViewModel: ArticlesViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.articlesViewModel = ArticlesViewModel()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> Schedulers.trampoline() }
    }

    @Test
    fun fetchArticles_positiveResponse() {

        val obj = MostPopularArticlesResponse("OK", "hh", 1, ArrayList<Result>())
        // Mock API response
        doReturn(Single.just(obj)).`when`(useCase).invoke()
        // Attach fake observer
        val observer = mock(Observer::class.java) as Observer<MostPopularArticlesResponse>
        this.articlesViewModel.articlesResponse1.observeForever(observer)
        // Invoke
        this.articlesViewModel.getArticles()
        // Verify
        assertEquals("OK", this.articlesViewModel.articlesResponse1.value?.status)
    }


}