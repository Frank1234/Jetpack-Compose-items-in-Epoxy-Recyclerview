package nl.frank.jetpacktestapplication.ui

import androidx.lifecycle.LifecycleOwner
import com.airbnb.epoxy.TypedEpoxyController

typealias LifecycleProvider = () -> LifecycleOwner

class ListViewController : TypedEpoxyController<List<String>>() {

    var lifecycleProvider: LifecycleProvider? = null

    fun registerLifecycleOwnerProvider(lifecycleProvider: LifecycleProvider) {
        this.lifecycleProvider = lifecycleProvider
    }

    override fun buildModels(
        titles: List<String>,
    ) {

        val lifecycle = lifecycleProvider?.invoke() ?: throw Error("No lifecycle given")

        titles.forEachIndexed { index, item ->
            buildTitleListItem(item, lifecycle, "$index")
        }
    }
}