package id.buaja.covid19.ui.province

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.buaja.covid19.R
import id.buaja.covid19.network.model.ProvinsiResponse
import kotlinx.android.synthetic.main.item_province.view.*

/**
 * Created By Julsapargi Nursam 3/27/20
 */

class ProvinceAdapter(
    private val data: List<ProvinsiResponse>
) : RecyclerView.Adapter<ProvinceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_province, parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ProvinsiResponse) {
            with(itemView) {
                tvProvince.text = item.attributes?.provinsi
                tvConfirmed.text = item.attributes?.kasusPosi.toString()
                tvRecovered.text = item.attributes?.kasusSemb.toString()
                tvDeaths.text = item.attributes?.kasusMeni.toString()
            }
        }
    }
}