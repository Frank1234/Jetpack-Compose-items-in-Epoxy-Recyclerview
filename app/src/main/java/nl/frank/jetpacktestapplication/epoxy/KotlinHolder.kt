package nl.frank.jetpacktestapplication.epoxy

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Copied shamelessly from: https://github.com/airbnb/epoxy/blob/a5620f9f8f96efd8ef99b70550bcfd3aa1e1a989/epoxy-preloadersample/src/main/java/com/airbnb/epoxy/preloadersample/KotlinHolder.kt
 */
abstract class KotlinHolder : EpoxyHolder() {

    private lateinit var view: View

    override fun bindView(itemView: View) {
        view = itemView
    }

    protected fun <V : View> bind(id: Int): ReadOnlyProperty<KotlinHolder, V> =
        Lazy { holder: KotlinHolder, prop ->
            holder.view.findViewById(id) as V?
                ?: throw IllegalStateException("View ID $id for '${prop.name}' not found.")
        }

    protected fun <V : View> bindRoot(): ReadOnlyProperty<KotlinHolder, V> =
        Lazy { holder: KotlinHolder, _ ->
            @Suppress("UNCHECKED_CAST")
            holder.view as? V? ?: throw IllegalStateException("Root not found or of invalid type.")
        }

    /**
     * Taken from Kotterknife.
     * https://github.com/JakeWharton/kotterknife
     */
    private class Lazy<V>(private val initializer: (KotlinHolder, KProperty<*>) -> V) : ReadOnlyProperty<KotlinHolder, V> {

        private object EMPTY

        private var value: Any? = EMPTY

        override fun getValue(thisRef: KotlinHolder, property: KProperty<*>): V {
            if (value == EMPTY) {
                value = initializer(thisRef, property)
            }
            @Suppress("UNCHECKED_CAST")
            return value as V
        }
    }
}
