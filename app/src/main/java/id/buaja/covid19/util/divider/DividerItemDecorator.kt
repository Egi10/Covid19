package id.buaja.covid19.util.divider

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created By Julsapargi Nursam 5/16/20
 */

class DividerItemDecorator(private val drawable: Drawable?) : RecyclerView.ItemDecoration() {
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0..childCount - 2) {
            val child: View = parent.getChildAt(i)
            val params =
                child.layoutParams as RecyclerView.LayoutParams
            val dividerTop: Int = child.bottom + params.bottomMargin
            drawable?.intrinsicHeight?.let {
                val dividerBottom: Int = dividerTop + drawable.intrinsicHeight
                drawable.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
                drawable.draw(c)
            }
        }
    }
}