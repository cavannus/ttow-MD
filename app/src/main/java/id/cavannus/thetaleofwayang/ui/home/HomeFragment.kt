package id.cavannus.thetaleofwayang.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.cavannus.thetaleofwayang.R
import id.cavannus.thetaleofwayang.classifier.GetCameraActivity
import id.cavannus.thetaleofwayang.classifier.GetGalleryActivity
import id.cavannus.thetaleofwayang.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private lateinit var homeViewModel: HomeViewModel

    lateinit var btnCamera: Button
    lateinit var btnGaleri: Button
    private val mGalleryRequestCode = 2

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCamera = view.findViewById(R.id.btn_camera)
	    btnCamera.setOnClickListener(this)
	    btnGaleri = view.findViewById(R.id.btn_gallery)
	    btnGaleri.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_gallery -> {
                val mIntent = Intent(activity, GetGalleryActivity::class.java)
                startActivity(mIntent)
            }

            R.id.btn_camera -> {
                val mIntent = Intent(activity, GetCameraActivity::class.java)
                startActivity(mIntent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}