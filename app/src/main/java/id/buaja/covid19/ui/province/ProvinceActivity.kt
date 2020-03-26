package id.buaja.covid19.ui.province

import android.widget.Toast
import androidx.lifecycle.Observer
import id.buaja.covid19.R
import id.buaja.covid19.base.BaseActivity
import id.buaja.covid19.util.LoaderState
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProvinceActivity : BaseActivity() {
    private val viewModel: ProvinceViewModel by viewModel()

    override fun contentView(): Int {
        return R.layout.activity_province
    }

    override fun initObservable() {
        viewModel.state.observe(this, Observer {
            when (it) {
                is LoaderState.ShowLoading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }

                is LoaderState.HideLoading -> {
                    Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.province.observe(this, Observer {
            it?.let {

            }
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun initView() {

    }
}
