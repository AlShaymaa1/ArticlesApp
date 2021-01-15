# ArticlesApp

Task to display most popular articles
Display Articles Task Using MVVM , Navigation , Retrofit ,Picasso , SOLID , RXJava , Kotlin , Mockito ,Junit4 , Espress

///////when open app you should see splash screen then most popular articles screen then click on each article 
to see it's details////////

// on onCreateView ArticlesFragment we make a call to get most popular articles (call getArticles Method from ArticlesViewModel)
   in viewModel we invoke getArticles from MostPopularArticlesUseCase and run it on Schedulers.io (Background thread)
   and inject response on AndroidSchedulers.mainThread() using RXJAVA 
   MostPopularArticlesUseCase comnuicate with ArticlesRepository which is communicate with ArticlesApi(ServerGateWay)
   using Retrofit
   finally after data come we get response on subscribe ond inject it on articlesResponse LiveData
   and observe on result in ArticlesFragment and display it in recyclerView
// when click on each recyclerView Item we should see it's details like Article photo and title


Unit Test

/////////////////////////
Java Test (API) Using Mockito
////////////////////////

fetchArticles_positiveResponse test Case 

we mock every object we want to use it 
Mock API response then Attach fake observer then Invoke getArticles
finally verify Status 
if("OK") then Success

/////////////////////////
Android Test (UI) using Espresso
////////////////////////

test_isActivityInView test Case

test if Acivity VISIBLE

test_isHostFragmentVisible test Case

test if HostFragment VISIBLE



