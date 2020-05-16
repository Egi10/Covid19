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
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ITEM_IMAGE = 0
        private const val ITEM_IMAGE_LEFT = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_IMAGE -> {
                ViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
                )
            }

            else -> {
                ViewHolderRing(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_news_list, parent, false)
                )
            }
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_IMAGE -> {
                val imageHolder = holder as ViewHolder
                imageHolder.bind(data[position], listener)
            }

            else -> {
                val textHolder = holder as ViewHolderRing
                textHolder.bind(data[position], listener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            ITEM_IMAGE
        } else {
            ITEM_IMAGE_LEFT
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ArticlesItem, listener: (ArticlesItem) -> Unit) {
            with(itemView) {
                ivImage.loadImage(item.urlToImage.toString())
                tvTitle.text = item.title
                tvSourceName.text = item.source?.name
                tvPublishedAt.text = item.publishedAt?.dateFormatUtc()

                setOnClickListener {
                    listener(item)
                }
            }
        }
    }

    class ViewHolderRing(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ArticlesItem, listener: (ArticlesItem) -> Unit) {
            with(itemView) {
                ivImage.loadImage(item.urlToImage.toString())
                tvTitle.text = item.title
                tvSourceName.text = item.source?.name
                tvPublishedAt.text = item.publishedAt?.dateFormatUtc()

                setOnClickListener {
                    listener(item)
                }
            }
        }
    }
}