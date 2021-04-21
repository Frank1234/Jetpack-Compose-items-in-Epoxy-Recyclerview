package nl.frank.jetpacktestapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import nl.frank.jetpacktestapplication.databinding.FragmentHomeBinding
import nl.frank.jetpacktestapplication.ui.ListViewController

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val listViewController = ListViewController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        initRecyclerView()

        return root
    }

    private fun initRecyclerView() {

        listViewController.registerLifecycleOwnerProvider { viewLifecycleOwner }
        listViewController.setData(generateTestData())

        binding.recyclerList.setController(listViewController)
        binding.recyclerList.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // binding.recyclerList.adapter = null
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