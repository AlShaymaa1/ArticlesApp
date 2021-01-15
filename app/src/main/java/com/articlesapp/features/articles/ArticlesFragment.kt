package com.articlesapp.features.articles

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.articlesapp.R
import com.articlesapp.model.Result
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_articles.view.*


class ArticlesFragment : Fragment() {

    private lateinit var  progressDialog:Dialog
    private lateinit var viewOfLayout: View
    private lateinit var viewModel: ArticlesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewOfLayout= inflater.inflate(R.layout.fragment_articles, container, false)
        viewModel = ViewModelProvider(this)[ArticlesViewModel::class.java]
        initProgressDialog()
        viewModel.getArticles()

        return viewOfLayout
    }

    private fun observeArticles() {
        val d: Disposable = viewModel.articlesResponse.subscribe {
           it.results?.let { it1 -> initializeTArticlesRecycler(it1) }
            }
        }

    private fun initializeTArticlesRecycler(list: List<Result>) {
        val articlesAdapter = ArticlesAdapter(
            list,
            Navigation.findNavController(
                requireActivity(),
                R.id.nav_host_fragment
            )
        )
        viewOfLayout.rv_articles.layoutManager = LinearLayoutManager(activity)
        viewOfLayout.rv_articles.adapter = articlesAdapter
    }
    private fun initProgressDialog() {
         progressDialog = activity?.let { Dialog(it) }!!
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(true)
        progressDialog.setContentView(R.layout.loading_dialog)
        progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }
    private fun observeLoading() {
        viewModel.isLoading.observe(this, Observer {
            it?.let {
                if (it) progressDialog.show()
                else progressDialog.dismiss()
            }
        })
    }
    override fun onStart() {
        super.onStart()
        observeArticles()
        observeLoading()
    }

}