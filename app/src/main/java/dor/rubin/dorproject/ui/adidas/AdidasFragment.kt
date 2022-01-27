package dor.rubin.dorproject.ui.adidas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dor.rubin.dorproject.MainActivity
import dor.rubin.dorproject.R
import dor.rubin.dorproject.adapter.AdidasAdapter

import dor.rubin.dorproject.databinding.FragmentSlideshowBinding

class AdidasFragment : Fragment() {

    private lateinit var slideshowViewModel: AdidasViewModel
    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProvider(this).get(AdidasViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        slideshowViewModel.liveAdidas.observe(viewLifecycleOwner){adidas->
            binding.progressBarAdidas.visibility =View.GONE
            binding.rvAdidas.adapter = AdidasAdapter(adidas)
            binding.rvAdidas.layoutManager = GridLayoutManager(requireContext(),2)
        }

    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar?.title = "Adidas"

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //I do not use the images only for printing while I clean or start the application,
    // the identity card of the images changes and as soon
    // as I print the images or use another the certificate of the images does not change
    private fun printImage() {
        val adidas1 = R.drawable.adidas_shoe_black_whit
        val adidas2 = R.drawable.stan_smith_white_blue
        val adidas3 = R.drawable.adidas_shoe_black
        val adidas4 = R.drawable.adidas_shoe_blacksa
        val adidas5 = R.drawable.adidas_cafcaf
        val adidas6 = R.drawable.adidas_shoe_white_with
        val adidas7 = R.drawable.adidas_swear_stan
        val adidas8 = R.drawable.adidas_coat_black
        val adidas9 = R.drawable.adidas_shirt_purple
        val adidas10 = R.drawable.adidas_swear_red
        val adidas11 = R.drawable.adidas_shirt_green
        val adidas12 = R.drawable.adids_shirt_white

        println(adidas1)
        println(adidas2)
        println(adidas3)
        println(adidas4)
        println(adidas5)
        println(adidas6)
        println(adidas7)
        println(adidas8)
        println(adidas9)
        println(adidas10)
        println(adidas11)
        println(adidas12)
    }
}