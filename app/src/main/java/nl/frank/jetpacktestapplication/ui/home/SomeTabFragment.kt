package nl.frank.jetpacktestapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import nl.frank.jetpacktestapplication.R
import nl.frank.jetpacktestapplication.databinding.FragmentSomeTabBinding
import nl.frank.jetpacktestapplication.ui.ListViewController
import kotlin.random.Random

class SomeTabFragment : Fragment() {

    private var _binding: FragmentSomeTabBinding? = null
    private val listViewController = ListViewController { viewLifecycleOwner }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSomeTabBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.textHome.text =
            "Fragment Instance: $this \n\nviewLifecycleOwner instance: $viewLifecycleOwner"
        binding.textHome.setOnClickListener {
            val recyclerView = binding.recyclerList
            Log.d("Test", "Title clicked $recyclerView")
        }

        binding.buttonNavigate.setOnClickListener {
            findNavController().navigate(R.id.action_test)
        }

        initRecyclerView()

        return root
    }

    private fun initRecyclerView() {

        listViewController.setData(generateTestData())

        binding.recyclerList.setController(listViewController)
        binding.recyclerList.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerList.adapter = null
        _binding = null
    }
}

fun generateTestData(): List<String> {
    val data = mutableListOf<String>()
    repeat(111) {
        if (Random.nextInt(3) == 2) {
            data.add("Title $it")
        } else {
            data.add("Item $it")
        }
    }
    return data.toList()
}