package nl.frank.jetpacktestapplication.ui

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.airbnb.epoxy.TypedEpoxyController

typealias LifecycleOwnerProvider = () -> LifecycleOwner

class ListViewController(private val lifecycleProvider: LifecycleOwnerProvider) :
    TypedEpoxyController<List<String>>() {

    init {
        Log.d("Test", "New ListViewController constructed")
    }

    override fun buildModels(
        titles: List<String>,
    ) {
        titles.forEachIndexed { index, item ->
            if (item.startsWith("Title")) {
                buildTitleListItem(item, lifecycleProvider, "$index")
            } else {
                buildOtherListItem(item, "$index")
            }
        }
    }
}