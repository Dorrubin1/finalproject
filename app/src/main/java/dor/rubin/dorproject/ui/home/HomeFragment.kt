package dor.rubin.dorproject.ui.home

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dor.rubin.dorproject.MainActivity
import dor.rubin.dorproject.R
import dor.rubin.dorproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var handler: Handler = Handler()
    var runnable: Runnable? = null
    var delay = 5000 // 5 seconds

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar?.title = "Home"
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //every 5 seconds the animations with the title are activated
        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, delay.toLong())


            binding.textFashion.animate().apply {
                duration = 2000
                rotationXBy(360f)

            }.start()
            binding.textTitle.animate().apply {
                duration = 2000
                rotationYBy(360f)
            }.start()

        }.also { runnable = it },delay.toLong())
        // on click button
        binding.buttonNike.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_gallery)
        }
        binding.buttonAdidas.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_slideshow)
        }
        binding.buttonConverse.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_converse)
        }

    }




    override fun onDestroyView() {
        super.onDestroyView()
    //   when Leaving the home page the animations stop
        if(runnable !=null)
        handler.removeCallbacks(runnable!!)
         _binding = null
    }
}