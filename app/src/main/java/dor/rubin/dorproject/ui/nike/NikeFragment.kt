package dor.rubin.dorproject.ui.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dor.rubin.dorproject.MainActivity
import dor.rubin.dorproject.R
import dor.rubin.dorproject.adapter.NikesAdapter
import dor.rubin.dorproject.databinding.FragmentGalleryBinding

class NikeFragment : Fragment() {

    private lateinit var galleryViewModel: NikeViewModel
    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        galleryViewModel =
            ViewModelProvider(this).get(NikeViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        galleryViewModel.liveNike.observe(viewLifecycleOwner){nike->
            binding.progressBarNike.visibility =View.GONE
            binding.rvShoes.adapter = NikesAdapter(nike)
            binding.rvShoes.layoutManager =GridLayoutManager(requireContext(),2)
        }

    }



    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar?.title = "Nike"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //I do not use the images only for printing while I clean or start the application,
    // the identity card of the images changes and as soon
    // as I print the images or use another the certificate of the images does not change
    private fun printImage() {
        val nike1 = R.drawable.nike_waffle
        val nike2 = R.drawable.nike_air_force_white
        val nike3 = R.drawable.nike_blazer_blue_sky
        val nike4 = R.drawable.nike_blazer_red
        val nike5 = R.drawable.nike_baksetball_green
        val nike6 = R.drawable.nike_dunk_allcolors
        val nike7 = R.drawable.nike_black_niks
        val nike8 = R.drawable.nike_ball_red_balck
        val nike9 = R.drawable.nike_swear_red
        val nike10 = R.drawable.nike_pants_brown
        val nike11 = R.drawable.nike_swear_nba_white
        val nike12 = R.drawable.nike_swear_gray_black
        println(nike1)
        println(nike2)
        println(nike3)
        println(nike4)
        println(nike5)
        println(nike6)
        println(nike7)
        println(nike8)
        println(nike9)
        println(nike10)
        println(nike11)
        println(nike12)
    }
}