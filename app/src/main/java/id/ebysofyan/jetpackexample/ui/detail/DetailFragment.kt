package id.ebysofyan.jetpackexample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import gdk.ebysofyan.mpvexample.utils.ext.dateFormat
import id.ebysofyan.jetpackexample.R
import id.ebysofyan.jetpackexample.data.Article
import id.ebysofyan.jetpackexample.utils.Constants
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val navController = activity?.let { Navigation.findNavController(it, R.id.nav_host_fragment) }
        val article: Article? = arguments?.getParcelable(Constants.ARTICLE_KEY)
        Glide.with(view).load(article?.urlToImage).into(detail_image_article)
        detail_txt_title.text = article?.title
        detail_txt_info.text = "${article?.publishedAt?.dateFormat()} | ${article?.author} | ${article?.source?.name}"
        detail_txt_content.text = article?.content
    }

}