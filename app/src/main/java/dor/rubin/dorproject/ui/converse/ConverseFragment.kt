package dor.rubin.dorproject.ui.converse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.GridLayoutManager
import dor.rubin.dorproject.MainActivity

import dor.rubin.dorproject.R
import dor.rubin.dorproject.adapter.ConverseAdapter
import dor.rubin.dorproject.databinding.FragmentConverseBinding



class ConverseFragment : Fragment() {
    // This property is only valid between onCreateView and
    // onDestroyView.

    private lateinit var converseViewModel: ConverseViewModel
    private var _binding: FragmentConverseBinding? = null
    private val binding get() = _binding!!
     lateinit var image:ImageView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        converseViewModel =
            ViewModelProvider(this).get(ConverseViewModel::class.java)
        _binding = FragmentConverseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        converseViewModel.converseLive.observe(viewLifecycleOwner){converse->
            binding.progressBarConverse.visibility =View.GONE
            binding.rvConvers.adapter = ConverseAdapter(converse)
            binding.rvConvers.layoutManager = GridLayoutManager(requireContext(),2)
        }



    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar?.title = "Converse"
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //I do not use the images only for printing while I clean or start the application,
    // the identity card of the images changes and as soon
    // as I print the images or use another the certificate of the images does not change
    private fun printImage() {
        val converse1 = R.drawable.allstar_brown
        val converse2 = R.drawable.converse_high_white70
        val converse3 = R.drawable.converse_shoe_white_green
        val converse4 = R.drawable.converse_black_small
        val converse5 = R.drawable.converse_white_small
        val converse6 = R.drawable.converse_black_high
        val converse7 = R.drawable.converse_allcolor_high
        val converse8 = R.drawable.converse_brown_high
        val converse9 = R.drawable.converse_orange
        val converse10 = R.drawable.converse_black_allcolors
        val converse11 = R.drawable.converse_hat_black
        val converse12 = R.drawable.converse_white_red

        println(converse1)
        println(converse2)
        println(converse3)
        println(converse4)
        println(converse5)
        println(converse6)
        println(converse7)
        println(converse8)
        println(converse9)
        println(converse10)
        println(converse11)
        println(converse12)
    }



}