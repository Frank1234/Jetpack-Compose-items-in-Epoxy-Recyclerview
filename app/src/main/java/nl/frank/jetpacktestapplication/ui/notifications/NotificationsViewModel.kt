package nl.frank.jetpacktestapplication.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "The view from the last fragment was destroyed..."
    }
    val text: LiveData<String> = _text
}