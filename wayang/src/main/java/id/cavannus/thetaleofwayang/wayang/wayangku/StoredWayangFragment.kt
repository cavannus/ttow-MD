package id.cavannus.thetaleofwayang.wayang.wayangku

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.cavannus.thetaleofwayang.core.ui.WayangAdapter
import id.cavannus.thetaleofwayang.wayang.databinding.FragmentStoredWayangBinding
import id.cavannus.thetaleofwayang.wayang.detail.DetailWayangActivity
import id.cavannus.thetaleofwayang.wayang.di.wayangModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class StoredWayangFragment : Fragment() {

    private val storedViewModel: StoredWayangViewModel by viewModel()

    private var _binding: FragmentStoredWayangBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoredWayangBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(wayangModule)

        if (activity != null) {

            val storedAdapter = WayangAdapter()
            storedAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailWayangActivity::class.java)
                intent.putExtra(DetailWayangActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            storedViewModel.storedWayang.observe(viewLifecycleOwner) { wayang ->
                if (wayang != null){
                    storedAdapter.setData(wayang)
                    binding?.lottieEmpty?.visibility = if (wayang.isNotEmpty()) View.GONE else View.VISIBLE
                    binding?.textEmpty?.visibility = if (wayang.isNotEmpty()) View.GONE else View.VISIBLE
                }
            }

            with(binding?.rvWayangFav) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = storedAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}