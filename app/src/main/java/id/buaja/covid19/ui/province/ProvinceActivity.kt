package id.buaja.covid19.ui.province

import android.view.KeyEvent
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.buaja.covid19.R
import id.buaja.covid19.base.BaseActivity
import id.buaja.covid19.network.model.ProvinsiResponse
import id.buaja.covid19.ui.maps.MapsActivity
import id.buaja.covid19.util.startActivity
import kotlinx.android.synthetic.main.activity_province.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProvinceActivity : BaseActivity() {
    private val viewModel: ProvinceViewModel by viewModel()
    private lateinit var adapter: ProvinceAdapter
    private val listProvince: MutableList<ProvinsiResponse> = mutableListOf()

    override fun contentView(): Int {
        return R.layout.activity_province
    }

    override fun initObservable() {
        viewModel.state.observe(this, Observer {
            swipeRefresh.isRefreshing = it
        })

        viewModel.province.observe(this, Observer {
            it?.let {
                listProvince.clear()
                listProvince.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun initView() {
        setSupportActionBar(toolbar)
        toolbar.title = "Daftar Provinsi"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        adapter = ProvinceAdapter(listProvince)
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
