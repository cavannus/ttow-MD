package id.cavannus.thetaleofwayang.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.cavannus.thetaleofwayang.R
import id.cavannus.thetaleofwayang.core.data.Resource
import id.cavannus.thetaleofwayang.core.ui.WayangAdapter
import id.cavannus.thetaleofwayang.databinding.FragmentGalleryBinding
import id.cavannus.thetaleofwayang.detail.DetailWayangActivity
import org.koin.android.viewmodel.ext.android.viewModel

class GalleryFragment : Fragment() {

    private val galleryViewModel: GalleryViewModel by viewModel()

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val tourismAdapter = WayangAdapter()
            tourismAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailWayangActivity::class.java)
                intent.putExtra(DetailWayangActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            galleryViewModel.wayang.observe(viewLifecycleOwner) { tourism ->
                if (tourism != null) {
                    when (tourism) {
                        is Resource.Loading<*> -> binding?.progressBar?.visibility = View.VISIBLE
                        is Resource.Success<*> -> {
                            binding?.progressBar?.visibility = View.GONE
                            tourismAdapter.setData(tourism.data)
                        }
                        is Resource.Error<*> -> {
                            binding?.progressBar?.visibility = View.GONE
                            binding?.viewError?.root?.visibility = View.VISIBLE
                            binding?.viewError?.tvError?.text =
                                tourism.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding?.rvWayang) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tourismAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}