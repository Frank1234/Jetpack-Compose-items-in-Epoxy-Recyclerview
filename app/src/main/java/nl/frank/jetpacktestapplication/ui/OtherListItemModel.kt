package nl.frank.jetpacktestapplication.ui

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.airbnb.epoxy.EpoxyController
import nl.frank.jetpacktestapplication.R
import nl.frank.jetpacktestapplication.databinding.OtherRowItemBinding
import nl.frank.jetpacktestapplication.epoxy.SpanSize
import nl.frank.jetpacktestapplication.epoxy.ViewBindingKotlinModel
import java.util.Random

data class OtherListItemModel(val viewState: String) :
    ViewBindingKotlinModel<OtherRowItemBinding>(R.layout.other_row_item, SpanSize.TOTAL) {

    override fun OtherRowItemBinding.bind() {
        val composablesInflated = composeItemTest.childCount > 0
        Log.d(
            "Test",
            "Other bind() called. $composeItemTest , composablesInflated $composablesInflated, hasComposition ${composeItemTest.hasComposition}, viewState $viewState"
        )

        composeItemTest.setContent {
            // In Compose world
            MaterialTheme {
                val x by rememberSaveable { mutableStateOf(Random().nextInt()) }
                Text("Composable $viewState rememberSaveable = $x")
            }

            DisposableEffect(true) {
                onDispose {
                    Log.w("Test", "Composable $viewState , $composeItemTest DISPOSED!")
                }
            }
        }
    }

    override fun unbind(view: View) {
        super.unbind(view)
    }

    override fun buildView(parent: ViewGroup): View {
        Log.d(
            "Test",
            "Other buildView() called"
        )
        // default ViewCompositionStrategy is destroy on unattach from window, but that happends in a recyclerview all the time. We destroy on fragment's view lifecycle destroy:
        return super.buildView(parent).apply {
            findViewById<ComposeView>(R.id.compose_item_test).setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )
        }
    }
}

fun EpoxyController.buildOtherListItem(
    viewState: String,
    id: String,
) {
    OtherListItemModel(viewState = viewState)
        .id(id)
        .addTo(this)
}