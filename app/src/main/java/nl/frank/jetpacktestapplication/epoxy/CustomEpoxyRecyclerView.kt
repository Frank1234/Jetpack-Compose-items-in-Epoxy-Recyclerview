package nl.frank.jetpacktestapplication.epoxy

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.EpoxyRecyclerView

class CustomEpoxyRecyclerView @kotlin.jvm.JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : EpoxyRecyclerView(context, attrs, defStyleAttr) {

    override fun shouldShareViewPoolAcrossContext(): Boolean {
        // weird stuff happends with a shared pool per activity, for some reason
        return false
    }
}