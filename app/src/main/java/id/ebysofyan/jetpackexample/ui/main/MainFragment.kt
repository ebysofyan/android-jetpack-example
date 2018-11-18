package id.ebysofyan.jetpackexample.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.ebysofyan.jetpackexample.R
import id.ebysofyan.jetpackexample.data.Article
import id.ebysofyan.jetpackexample.data.BaseResponse
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Response
import id.ebysofyan.jetpackexample.utils.Constants

class MainFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController
    private lateinit var adapter: MainRecyclerAdapter

    private val articles = mutableListOf<Article>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getArticles()

        viewModel.getLoading().observe(this, Observer {
            onLoading(it)
        })

        viewModel.getResponseLiveData()?.observe(this, Observer { response ->
            onDataLoaded(response)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        main_swipe_layout.setOnRefreshListener(this)

        navController = Navigation.findNavController(activity!!, R.id.nav_host_fragment)
    }

    private fun onLoading(loading: Boolean) {
        Log.e("LOADING_STATUS", loading.toString())

        main_swipe_layout.isRefreshing = loading
    }

    private fun onDataLoaded(response: Response<BaseResponse<Article>>) {
        response.body()?.articles?.let {
            adapter.addAll(it)
        }
    }

    private fun initRecyclerView() {
        main_rc_view.layoutManager = LinearLayoutManager(context)
        adapter = MainRecyclerAdapter(articles) {
            val bundle = Bundle()
            bundle.putParcelable(Constants.ARTICLE_KEY, it)

            navController?.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }

        main_rc_view.adapter = adapter
    }

    override fun onRefresh() {
        adapter.removeAll()
        viewModel.getArticles()
    }

}
