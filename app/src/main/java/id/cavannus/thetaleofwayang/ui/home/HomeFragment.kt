package id.cavannus.thetaleofwayang.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.cavannus.thetaleofwayang.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

//class HomeFragment : Fragment() {
//
//    private val homeViewModel: HomeViewModel by viewModel()
//
//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        if (activity != null) {
//
//            val tourismAdapter = WayangAdapter()
//            tourismAdapter.onItemClick = { selectedData ->
//                val intent = Intent(activity, DetailWayangActivity::class.java)
//                intent.putExtra(DetailWayangActivity.EXTRA_DATA, selectedData)
//                startActivity(intent)
//            }
//
//            homeViewModel.wayang.observe(viewLifecycleOwner) { tourism ->
//                if (tourism != null) {
//                    when (tourism) {
//                        is Resource.Loading<*> -> binding.progressBar.visibility = View.VISIBLE
//                        is Resource.Success<*> -> {
//                            binding.progressBar.visibility = View.GONE
//                            tourismAdapter.setData(tourism.data)
//                        }
//                        is Resource.Error<*> -> {
//                            binding.progressBar.visibility = View.GONE
//                            binding.viewError.root.visibility = View.VISIBLE
//                            binding.viewError.tvError.text =
//                                tourism.message ?: getString(R.string.something_wrong)
//                        }
//                    }
//                }
//            }
//
//            with(binding.rvMeal) {
//                layoutManager = LinearLayoutManager(context)
//                setHasFixedSize(true)
//                adapter = tourismAdapter
//            }
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}