package id.cavannus.thetaleofwayang.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.cavannus.thetaleofwayang.core.ui.WayangAdapter
import id.cavannus.thetaleofwayang.detail.DetailWayangActivity
import id.cavannus.thetaleofwayang.favorite.databinding.FragmentFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        if (activity != null) {

            val wayangAdapter = WayangAdapter()
            wayangAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailWayangActivity::class.java)
                intent.putExtra(DetailWayangActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.favoriteWayang.observe(viewLifecycleOwner) { dataWayang ->
                wayangAdapter.setData(dataWayang)
                binding.ivEmpty.visibility = if (dataWayang.isNotEmpty()) View.GONE else View.VISIBLE
                binding.textEmpty.visibility = if (dataWayang.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvWayangFav) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = wayangAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}