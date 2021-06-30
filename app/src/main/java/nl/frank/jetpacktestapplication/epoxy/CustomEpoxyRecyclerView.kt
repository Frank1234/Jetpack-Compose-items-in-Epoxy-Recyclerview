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
        // a shared pool per activity does not work well in combination with ComposeViews at the moment.
        // when we do use a shared pool: composeView.setContent in an item sometimes results in an empty view.
        return false
    }
}