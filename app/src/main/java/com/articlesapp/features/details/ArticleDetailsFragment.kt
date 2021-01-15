package com.articlesapp.features.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.articlesapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_article_details.view.*


class ArticleDetailsFragment : Fragment() {

    private lateinit var viewOfLayout: View
    private val args: ArticleDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewOfLayout= inflater.inflate(R.layout.fragment_article_details, container, false)
        val posterImage = args.articleDetails.media?.get(0)?.mediaMetadata?.get(1)?.url
        Picasso.with(activity).load(posterImage).into(viewOfLayout.iv_article)
        viewOfLayout.tv_published_date.text=args.articleDetails.publishedDate
        viewOfLayout.tv_title.text=args.articleDetails.title
        viewOfLayout.tv_copy_right.text= args.articleDetails.media?.get(0)?.copyright ?:""
        viewOfLayout.tv_caption.text= args.articleDetails.media?.get(0)?.caption ?:""
        viewOfLayout.tv_source.text= args.articleDetails.source
        viewOfLayout.tv_byline.text= args.articleDetails.byline
        return viewOfLayout
    }

}