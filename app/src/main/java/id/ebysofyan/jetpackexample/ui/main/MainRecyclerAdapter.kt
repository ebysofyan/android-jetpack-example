package id.ebysofyan.jetpackexample.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import gdk.ebysofyan.mpvexample.utils.ext.dateFormat
import id.ebysofyan.jetpackexample.R
import id.ebysofyan.jetpackexample.data.Article

class MainRecyclerAdapter(private val list: MutableList<Article>, private val listener: (Article) -> Unit) :
    RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.main_item_view, p0, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: MainViewHolder, p1: Int) {
        p0.bindItem(list[p1], listener)
    }

    fun addAll(articles : MutableList<Article>){
        list.addAll(articles)
        notifyDataSetChanged()
    }

    fun removeAll(){
        list.clear()
        notifyDataSetChanged()
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgHeader = view.findViewById<ImageView>(R.id.img_article)
        val txtTitle = view.findViewById<TextView>(R.id.txt_title)
        val txtDetail = view.findViewById<TextView>(R.id.txt_detail)
        val txtContent = view.findViewById<TextView>(R.id.txt_content)

        fun bindItem(article: Article, listener: (Article) -> Unit) {
            val option = RequestOptions().apply {
                placeholder(R.drawable.no_image)
            }
            Glide.with(itemView.context).load(article.urlToImage).apply(option).into(imgHeader)
            txtTitle.text = article.title
            txtDetail.text = "${article.publishedAt?.dateFormat()} | ${article.author} | ${article.source?.name}"
            txtContent.text = article.content

            itemView.setOnClickListener {
                listener(article)
            }
        }
    }
}