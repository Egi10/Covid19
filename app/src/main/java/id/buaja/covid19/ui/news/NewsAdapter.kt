package id.buaja.covid19.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.buaja.covid19.R
import id.buaja.covid19.network.model.news.ArticlesItem
import id.buaja.covid19.util.dateFormatUtc
import id.buaja.covid19.util.loadImage
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * Created By Julsapargi Nursam 3/28/20
 */


class NewsAdapter(
    private val data: List<ArticlesItem>,
    private val listener: (ArticlesItem) -> Unit
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], listener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ArticlesItem, listener: (ArticlesItem) -> Unit) {
            with(itemView) {
                ivImage.loadImage(item.urlToImage.toString())
                tvTitle.text = item.title
                tvSourceName.text = item.source?.name
                tvDescription.text = item.description
                tvPublishedAt.text = item.publishedAt?.dateFormatUtc()

                setOnClickListener {
                    listener(item)
                }
            }
        }
    }
}