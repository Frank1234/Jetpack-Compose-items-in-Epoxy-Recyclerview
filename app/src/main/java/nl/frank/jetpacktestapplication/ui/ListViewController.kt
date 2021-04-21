package nl.frank.jetpacktestapplication.ui

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.airbnb.epoxy.TypedEpoxyController

typealias LifecycleOwnerProvider = () -> LifecycleOwner

class ListViewController(private val lifecycleProvider: LifecycleOwnerProvider) : TypedEpoxyController<List<String>>() {

    init {
        Log.d("Init you farnk", "Init you frank")
    }

    override fun buildModels(
        titles: List<String>,
    ) {

        val lifecycleProvider = lifecycleProvider ?: throw Error("No lifecycle given")

        titles.forEachIndexed { index, item ->
            buildTitleListItem(item, lifecycleProvider, "$index")
        }
    }
}