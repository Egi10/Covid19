package id.buaja.covid19.ui.news

import android.view.KeyEvent
import android.view.MenuItem
import id.buaja.covid19.R
import id.buaja.covid19.base.BaseActivity
import id.buaja.covid19.ui.maps.MapsActivity
import id.buaja.covid19.util.startActivity

class NewsActivity : BaseActivity() {
    override fun contentView(): Int {
        return R.layout.activity_news
    }

    override fun initObservable() {

    }

    override fun initView() {

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
