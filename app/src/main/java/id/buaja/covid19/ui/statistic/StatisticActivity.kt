package id.buaja.covid19.ui.statistic

import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Line
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.Anchor
import com.anychart.enums.MarkerType
import com.anychart.enums.TooltipPositionMode
import com.anychart.graphics.vector.Stroke
import id.buaja.covid19.R
import id.buaja.covid19.base.BaseActivity
import id.buaja.covid19.ui.maps.MapsActivity
import id.buaja.covid19.util.LoaderState
import id.buaja.covid19.util.startActivity
import kotlinx.android.synthetic.main.activity_statistic.*
import kotlinx.android.synthetic.main.layout_empty.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class StatisticActivity : BaseActivity() {
    private val viewModel: StatisticViewModel by viewModel()
    private var seriesData: MutableList<DataEntry> = ArrayList()

    override fun contentView(): Int {
        return R.layout.activity_statistic
    }

    override fun initObservable() {
        with(viewModel) {
            state.observe(this@StatisticActivity, Observer {
                when(it) {
                    is LoaderState.ShowLoading -> {
                        progressBar.visibility = View.VISIBLE
                    }

                    is LoaderState.HideLoading -> {
                        progressBar.visibility = View.GONE
                    }
                }

            })

            statistic.observe(this@StatisticActivity, Observer {
                val cartesian: Cartesian = AnyChart.line()

                cartesian.animation(true)

                cartesian.padding(10.0, 20.0, 5.0, 20.0)

                cartesian.crosshair().enabled(true)
                cartesian.crosshair()
                    .yLabel(true)
                    .yStroke(null as Stroke?, null, null, null as String?, null as String?)

                cartesian.tooltip().positionMode(TooltipPositionMode.POINT)

                cartesian.title("Data Perkembangan Kasus Tiap Hari Di Indonesia")

                cartesian.yAxis(0).title("Perkembangan Setiap Hari")
                cartesian.xAxis(0).labels().padding(5.0, 5.0, 5.0, 5.0)

                for (i in it.indices) {
                    seriesData.add(CustomDataEntry(it[i].date, it[i].totalCases, it[i].totalRecoveries, it[i].totalDeaths))
                }

                val set = Set.instantiate()
                set.data(seriesData)
                val series1Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value' }")
                val series2Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value2' }")
                val series3Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value3' }")

                val series1: Line = cartesian.line(series1Mapping)
                series1.name("Positif")
                series1.hovered().markers().enabled(true)
                series1.hovered().markers()
                    .type(MarkerType.CIRCLE)
                    .size(4.0)
                series1.tooltip()
                    .position("right")
                    .anchor(Anchor.LEFT_CENTER)
                    .offsetX(5.0)
                    .offsetY(5.0)

                val series2: Line = cartesian.line(series2Mapping)
                series2.name("Sembuh")
                series2.hovered().markers().enabled(true)
                series2.hovered().markers()
                    .type(MarkerType.CIRCLE)
                    .size(4.0)
                series2.tooltip()
                    .position("right")
                    .anchor(Anchor.LEFT_CENTER)
                    .offsetX(5.0)
                    .offsetY(5.0)

                val series3: Line = cartesian.line(series3Mapping)
                series3.name("Meninggal")
                series3.hovered().markers().enabled(true)
                series3.hovered().markers()
                    .type(MarkerType.CIRCLE)
                    .size(4.0)
                series3.tooltip()
                    .position("right")
                    .anchor(Anchor.LEFT_CENTER)
                    .offsetX(5.0)
                    .offsetY(5.0)

                cartesian.legend().enabled(true)
                cartesian.legend().fontSize(13.0)
                cartesian.legend().padding(0.0, 0.0, 10.0, 0.0)

                anyChartView.setChart(cartesian)
            })

            error.observe(this@StatisticActivity, Observer {
                empty.visibility = View.VISIBLE
                tvMessage.text = it
            })
        }
    }

    override fun initView() {
        toolbar.title = "Statistik"
    }

    private class CustomDataEntry internal constructor(
        x: String?,
        value: Number?,
        value2: Number?,
        value3: Number?
    ) :
        ValueDataEntry(x, value) {
        init {
            setValue("value2", value2)
            setValue("value3", value3)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(MapsActivity::class.java)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            startActivity(MapsActivity::class.java)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
