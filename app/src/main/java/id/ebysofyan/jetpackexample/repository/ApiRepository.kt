package gdk.ebysofyan.mpvexample.repository

import id.ebysofyan.jetpackexample.data.Article
import id.ebysofyan.jetpackexample.data.BaseResponse
import id.ebysofyan.jetpackexample.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRepository {
    @GET("/v2/everything")
    fun getArticles(@Query("apiKey") apiKey: String? = Constants.API_KEY, @Query("q") q: String? = "bitcoin"): Call<BaseResponse<Article>>
}