package nl.frank.jetpacktestapplication.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nl.frank.jetpacktestapplication.databinding.FragmentTestBinding

class TestFragment : Fragment() {

    private var _binding: FragmentTestBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.textMessage.text = "The view from the last fragment was destroyed..."

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}