package id.buaja.covid19.ui.statistic

import androidx.lifecycle.Observer
import id.buaja.covid19.R
import id.buaja.covid19.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatisticActivity : BaseActivity() {
    private val viewModel: StatisticViewModel by viewModel()

    override fun contentView(): Int {
        return R.layout.activity_statistic
    }

    override fun initObservable() {
        with(viewModel) {
            state.observe(this@StatisticActivity, Observer {

            })

            statistic.observe(this@StatisticActivity, Observer {

            })
        }
    }

    override fun initView() {

    }
}
