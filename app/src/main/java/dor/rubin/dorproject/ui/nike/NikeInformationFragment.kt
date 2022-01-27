package dor.rubin.dorproject.ui.nike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import dor.rubin.dorproject.MainActivity
import dor.rubin.dorproject.R
import dor.rubin.dorproject.adapter.SizeListAdapter
import dor.rubin.dorproject.models.Cart
import dor.rubin.dorproject.models.SizeRepository

import dor.rubin.dorproject.viewmodels.CartViewModel
import kotlinx.android.synthetic.main.fragment_shoes_info.*

class NikeInformationFragment : Fragment() {
    lateinit var spinner: Spinner
    private val args: NikeInformationFragmentArgs by navArgs()
    lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shoes_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // All information of the product
        val code = args.shoesCodeArguments
        val name = args.nikeNameArgument
        val price = args.nikePriceArgument
        val description = args.nikeDescriptionArgument
        val image = args.nikeImageArgument
        val sizeB = args.nikeSizeBArgument
        spinner = view.findViewById<Spinner>(R.id.spinner)
        var list = SizeRepository.getClothesSize()
        // if nikeSizeB true show size of shoes else size of clothes
        if (sizeB) {
            list = SizeRepository.getShoesSize()
        }
        val adapter = SizeListAdapter(requireContext(), list)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerResult = list[position]
                // At the click of a button, the item moves to the shopping cart with the information
                // It is not possible to press the button twice so that items are not added
                // accidentally only after refreshing the page it will be possible to press the button
                btn_add.setOnClickListener {
                    println(spinnerResult.size)
                    val alpha = it.alpha
                    if (alpha == 1f) {
                        it.alpha = 0.5f
                        Toast.makeText(
                            context,
                            "$name successfully added to cart", Toast.LENGTH_LONG
                        ).show()
                        viewModel.addCart(
                            Cart(
                                code, name,
                                price, image, spinnerResult.size
                            )
                        )
                    }

                }

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }


        // attached sizeListAdapter to spinner
        spinner.adapter = adapter
        viewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]
        // Displays the information passing through the argument
        text_name_info.text = name
        text_description.text = description
        text_price_info.text = "Price: ₪ $price"
        image_shoe_information.setImageResource(image)

    }


     // change the toolbar title
    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar?.title = "Product information"

    }



}


