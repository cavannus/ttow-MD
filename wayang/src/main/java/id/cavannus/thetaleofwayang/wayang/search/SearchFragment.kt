package id.cavannus.thetaleofwayang.wayang.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.cavannus.thetaleofwayang.core.data.Resource
import id.cavannus.thetaleofwayang.core.ui.WayangAdapter
import id.cavannus.thetaleofwayang.wayang.R
import id.cavannus.thetaleofwayang.wayang.databinding.FragmentSearchBinding
import id.cavannus.thetaleofwayang.wayang.detail.DetailWayangActivity
import id.cavannus.thetaleofwayang.wayang.di.wayangModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class SearchFragment : Fragment() {
    private val searchViewModel: SearchViewModel by viewModel()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(wayangModule)

        if(activity != null){
            val searchAdapter = WayangAdapter()
            searchAdapter.onItemClick = { selectedData ->
                searchViewModel.getFavorite(selectedData.nm_wayang)
                        .observe(viewLifecycleOwner) { wayang ->
                            if(wayang != null){
                                selectedData.isFavorite = wayang.isFavorite
                            }

                            val intent = Intent(activity, DetailWayangActivity::class.java)
                            intent.putExtra(DetailWayangActivity.EXTRA_DATA, selectedData)
                            startActivity(intent)
                        }
            }

            val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
            val searchView = binding?.svWayang

            searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            searchView?.setOnQueryTextListener(
                    object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            searchView.clearFocus()
                            if (query != null) {
                                searchViewModel.searchWayang(query)
                                        .observe(viewLifecycleOwner) { wayang ->
                                    when(wayang) {
                                        is Resource.Loading -> {
                                            binding?.lottieError?.visibility = View.GONE
                                            binding?.textError?.visibility = View.GONE
                                            binding?.progressBar?.visibility = View.VISIBLE
                                        }
                                        is Resource.Success -> {
                                            binding?.progressBar?.visibility = View.GONE
                                            binding?.lottieError?.visibility = View.GONE
                                            binding?.textError?.visibility = View.GONE
                                            binding?.rvWayang?.visibility = View.VISIBLE
                                            searchAdapter.setData(wayang.data)
                                        }
                                        is Resource.Error -> {
                                            binding?.rvWayang?.visibility = View.GONE
                                            binding?.progressBar?.visibility = View.GONE
                                            binding?.lottieError?.visibility = View.VISIBLE
                                            binding?.textError?.visibility = View.VISIBLE
                                            binding?.textError?.text = resources.getString(R.string.search_error)
                                        }
                                    }
                                }
                            }
                            return true
                        }

                        override fun onQueryTextChange(query: String?): Boolean {
                            return false
                        }
                    }
            )

            with(binding?.rvWayang){
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = searchAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}