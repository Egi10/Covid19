package id.buaja.covid19.ui

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
import id.buaja.covid19.util.LoaderState
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.layout_information.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MapsActivity : BaseActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val viewModelMaps: MapsViewModel by viewModel()
    private var list: ArrayList<ResponseConfirmed> = ArrayList()

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
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        list.let {
            for (i in it.indices) {
                val location = LatLng(it[i].lat!!.toDouble(), it[i].jsonMemberLong!!.toDouble())
                val snippet =
                    "${it[i].confirmed}:${it[i].recovered}:${it[i].deaths}:${it[i].lastUpdate}"
                val markerOptions = MarkerOptions()
                    .position(location)
                    .title(it[i].countryRegion)
                    .snippet(snippet)
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_with_border))
                mMap.addMarker(markerOptions)
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
            }
        }

        googleMap.setOnMarkerClickListener {
            val split = it.snippet.toString()
            val parts = split.split(":")
            tvCountry.text = it.title
            tvConfirmed.text = parts[0]
            tvRecovered.text = parts[1]
            tvDeaths.text = parts[2]
            val input = parts[3]
            val inputFormat = SimpleDateFormat("ssssssssss", Locale.ENGLISH)
            val myDate = inputFormat.parse(input)

            val outputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
            myDate?.let {
                val myDateAsString = outputFormat.format(it)
                tvLastUpdate.text = myDateAsString
            }

            return@setOnMarkerClickListener false
        }
    }
}
