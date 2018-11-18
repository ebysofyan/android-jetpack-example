package id.ebysofyan.jetpackexample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gdk.ebysofyan.mpvexample.repository.ApiRepository
import gdk.ebysofyan.mpvexample.repository.Network
import id.ebysofyan.jetpackexample.data.Article
import id.ebysofyan.jetpackexample.data.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val responseLiveData: MutableLiveData<Response<BaseResponse<Article>>> = MutableLiveData()
    private val loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getResponseLiveData(): LiveData<Response<BaseResponse<Article>>>? = responseLiveData

    fun getLoading(): LiveData<Boolean> = loading

    private fun setLoading(newValue: Boolean) {
        loading.value = newValue
    }

    fun getArticles() {
        setLoading(true)

        val request = Network.retrofit.create(ApiRepository::class.java).getArticles()
        request.enqueue(object : Callback<BaseResponse<Article>> {
            override fun onFailure(call: Call<BaseResponse<Article>>, t: Throwable) {
                setLoading(false)
            }

            override fun onResponse(call: Call<BaseResponse<Article>>, response: Response<BaseResponse<Article>>) {
                responseLiveData.value = response
                setLoading(false)
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
    }
}
