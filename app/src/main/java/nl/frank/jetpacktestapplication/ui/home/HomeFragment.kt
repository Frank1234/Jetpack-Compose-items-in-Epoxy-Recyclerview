package nl.frank.jetpacktestapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import nl.frank.jetpacktestapplication.R
import nl.frank.jetpacktestapplication.databinding.FragmentHomeBinding
import nl.frank.jetpacktestapplication.ui.ListViewController

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val listViewController = ListViewController { viewLifecycleOwner }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.textHome.text =
            "Fragment Instance: $this \n\nviewLifecycleOwner instance: $viewLifecycleOwner"

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
        data.add("Item $it")
    }
    return data.toList()
}