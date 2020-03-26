package id.buaja.covid19.ui.maps

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import id.buaja.covid19.R
import id.buaja.covid19.base.BaseActivity
import id.buaja.covid19.network.model.ResponseConfirmed
import id.buaja.covid19.ui.province.ProvinceActivity
import id.buaja.covid19.util.LoaderState
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.layout_information.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MapsActivity : BaseActivity(), OnMapReadyCallback, View.OnClickListener {
    private lateinit var mMap: GoogleMap
    private var list: ArrayList<ResponseConfirmed> = ArrayList()
    private val viewModelMaps: MapsViewModel by viewModel()

    override fun contentView(): Int {
        return R.layout.activity_maps
    }

    override fun initObservable() {
        viewModelMaps.state.observe(this, Observer {
            when (it) {
                is LoaderState.ShowLoading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }

                is LoaderState.HideLoading -> {
                    Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModelMaps.confirmated.observe(this, Observer {
            it?.let {
                list.addAll(it)
                val mapFragment = supportFragmentManager
                    .findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this)
            }
        })

        viewModelMaps.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun initView() {
        viewModelMaps.getConfirmed()

        ivLoadData.setOnClickListener {
            viewModelMaps.getConfirmed()
        }

        window.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                }
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                statusBarColor = Color.TRANSPARENT
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val location = LatLng(list[0].lat!!.toDouble(), list[0].jsonMemberLong!!.toDouble())
        val markerOptions = MarkerOptions()
            .position(location)
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_with_border))
        mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 3.5f))

        tvCountry.text = list[0].countryRegion
        tvConfirmed.text = list[0].confirmed.toString()
        tvRecovered.text = list[0].recovered.toString()
        tvDeaths.text = list[0].deaths.toString()
        val input = list[0].lastUpdate.toString()
        val inputFormat = SimpleDateFormat("ssssssssssSSS", Locale.getDefault())
        val myDate = inputFormat.parse(input)

        val outputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
        myDate?.let {
            val myDateAsString = outputFormat.format(it)
            tvLastUpdate.text = myDateAsString
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.ivDaily) {
            startActivity(Intent(this, ProvinceActivity::class.java))
        }
    }
}