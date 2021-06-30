package nl.frank.jetpacktestapplication.ui

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.airbnb.epoxy.EpoxyController
import nl.frank.jetpacktestapplication.R
import nl.frank.jetpacktestapplication.databinding.TextRowItemBinding
import nl.frank.jetpacktestapplication.epoxy.SpanSize
import nl.frank.jetpacktestapplication.epoxy.ViewBindingKotlinModel

// viewState is just a title string
data class TitleListItemModel(val viewState: String) :
    ViewBindingKotlinModel<TextRowItemBinding>(R.layout.text_row_item, SpanSize.TOTAL) {

    lateinit var lifecycleOwnerProvider: LifecycleOwnerProvider

    fun lifecycleOwnerProvider(lifecycleOwnerProvider: LifecycleOwnerProvider): TitleListItemModel {
        this.lifecycleOwnerProvider = lifecycleOwnerProvider
        return this
    }

    override fun TextRowItemBinding.bind() {
        val composablesInflated = composeItemTest.childCount > 0
        Log.d(
            "Test",
            "bind() called. composablesInflated $composablesInflated, hasComposition ${composeItemTest.hasComposition}, viewState $viewState"
        )

        textView.text = "Traditional $viewState ${lifecycleOwnerProvider()}"

        composeItemTest.setContent {
            // In Compose world
            MaterialTheme {
                Text("Composable $viewState")
            }
        }
    }

    override fun unbind(view: View) {
        super.unbind(view)
    }

    override fun buildView(parent: ViewGroup): View {
        Log.d(
            "Test",
            "buildView() called"
        )
        // default ViewCompositionStrategy is destroy on unattach from window, but that happends in a recyclerview all the time. We destroy on fragment's view lifecycle destroy:
        return super.buildView(parent).apply {
            findViewById<ComposeView>(R.id.compose_item_test).setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(
                    lifecycleOwnerProvider()
                )
            )
        }
    }
}

fun EpoxyController.buildTitleListItem(
    viewState: String,
    lifecycleOwnerProvider: LifecycleOwnerProvider,
    id: String,
) {
    TitleListItemModel(viewState = viewState)
        .lifecycleOwnerProvider(lifecycleOwnerProvider)
        .id(id)
        .addTo(this)
}