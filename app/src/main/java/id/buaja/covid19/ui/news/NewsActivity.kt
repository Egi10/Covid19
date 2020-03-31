package id.buaja.covid19.ui.news

import android.net.Uri
import android.view.KeyEvent
import android.view.MenuItem
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.buaja.covid19.R
import id.buaja.covid19.base.BaseActivity
import id.buaja.covid19.network.model.news.ArticlesItem
import id.buaja.covid19.ui.maps.MapsActivity
import id.buaja.covid19.util.startActivity
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity : BaseActivity() {
    private val viewModel: NewsViewModel by viewModel()
    private val listNews: MutableList<ArticlesItem> = mutableListOf()
    private lateinit var adapter: NewsAdapter

    override fun contentView(): Int {
        return R.layout.activity_news
    }

    override fun initObservable() {
        viewModel.state.observe(this, Observer {
            swipeRefresh.isRefreshing = it
        })

        viewModel.news.observe(this, Observer {
            it?.let {
                listNews.clear()
                listNews.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun initView() {
        toolbar.title = "Berita Utama Corona"

        swipeRefresh.setOnRefreshListener {
            viewModel.getNews()
        }

        adapter = NewsAdapter(listNews) {
            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(it.url))
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            startActivity(MapsActivity::class.java)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(MapsActivity::class.java)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}