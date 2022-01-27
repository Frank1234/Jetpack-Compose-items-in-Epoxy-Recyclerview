# Try-out: using Jetpack Composables inside Epoxy Recyclerviews.

Ugly code, I tried/tested many things and left some of the code in here :).

## Main take-aways, to use Composables in Epoxy Recyclerviews:

1. Don't share a view-pool activity-wide, but use one per Recyclerview. (set
   in [CustomEpoxyRecyclerView](./app/src/main/java/nl/frank/jetpacktestapplication/epoxy/CustomEpoxyRecyclerView.kt))
2. Dispose of compositions with ViewCompositionStrategy attached to the owner's lifecycle, so that
   compositions are not disposed on every unbind (the default). You can do this easily using:
   [ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed](https://developer.android.com/reference/kotlin/androidx/compose/ui/platform/ViewCompositionStrategy.DisposeOnDetachedFromWindow?hl=nl)
   as shown
   in [TitleListItemModel](./app/src/main/java/nl/frank/jetpacktestapplication/ui/TitleListItemModel.kt)
3. After implementing, test that your Composables don't dispose or rebuild when you don't
   want/expect them to. When scrolling a list, they should only rebind.
4. When you add a Composable to a Horizontal Scrolling nested recyclerview, beware and test
   carefully, I did not test this.

Example item model
here: [TitleListItemModel](./app/src/main/java/nl/frank/jetpacktestapplication/ui/TitleListItemModel.kt)
