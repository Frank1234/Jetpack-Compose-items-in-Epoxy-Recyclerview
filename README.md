Try-out using Epoxy with Jetpack Compose Recyclerview item views.

Main take-aways:
1. Don't share a view-pool activity-wide, but use one per Recyclerview. (specified in CustomEpoxyRecyclerView)
2. Dispose of composition's with ViewCompositionStrategy attached to the owner's lifecycle, so that views are disposed eventually, but not on every unbind. 
