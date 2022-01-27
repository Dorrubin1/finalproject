package dor.rubin.dorproject.ui.shoppingcart

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dor.rubin.dorproject.MainActivity
import dor.rubin.dorproject.R

import dor.rubin.dorproject.adapter.CartAdapter
import dor.rubin.dorproject.adapter.CartItemClickedListener

import dor.rubin.dorproject.databinding.FragmentShoppingCartBinding
import dor.rubin.dorproject.models.Cart
import dor.rubin.dorproject.models.Payment

import dor.rubin.dorproject.viewmodels.CartViewModel
import dor.rubin.dorproject.viewmodels.PaymentViewModel
import io.card.payment.CardIOActivity
import io.card.payment.CreditCard



class ShoppingCartFragment : Fragment(), CartItemClickedListener {

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var cartViewModel: CartViewModel
    private lateinit var paymentViewModel: PaymentViewModel
    var total: Double = 0.0
    var totalCode: Float = 0.0F

    var message = ""
    private val paymentScreen =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val dialog = AlertDialog.Builder(context)
                .setTitle("Card Info")
            if (result.data != null && result.data!!.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                val cardResult: CreditCard? =
                    result.data!!.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT)
                // Displays the dialog with the information entered
                if (cardResult != null) {
                    if (cardResult.cardholderName != null) {
                        message += "Card Holder: ${cardResult.cardholderName}\n"
                    }
                    if (cardResult.cardNumber != null) {
                        message += "Card Number: ${cardResult.cardNumber}\n"
                    }
                    if (cardResult.isExpiryValid) {
                        message += "Card Expiration Date: ${cardResult.expiryMonth}/ ${cardResult.expiryYear}\n"
                    }
                    if (cardResult.cvv != null) {
                        message += "Card CVV: ${cardResult.cvv}\n"
                    }
                    message += "Price: ${(total * getDiscount()).toInt()}\n"
                }
                dialog.setMessage(message)
                // With the click of the OK button, the payment is made,
                // clears the shopping cart and returns to the home page
                dialog.setPositiveButton("Okay") { _, _ ->
                    if (cardResult != null) {
                        paymentViewModel.addPayment(
                            Payment(
                                cardResult.cardholderName,
                                (total.toFloat() * getDiscount()),
                                cardResult.cardNumber,
                                cardResult.expiryMonth.toString()
                                        + "/" + cardResult.expiryYear.toString(),
                                cardResult.cvv
                            )
                        )

                    }
                    cartViewModel.cleanTheCart()

                    findNavController().navigate(R.id.action_nav_shopping_cart_to_nav_home)


                }
                dialog.show()


            }


        }

    private fun getDiscount(): Double {
        return if (totalCode == 0.0f) {
            1.0
        } else {
            (0.83)
        }
    }

    // hide the keyBoard on click code button
    private fun hideKeyboard() {
        activity?.getSystemService(InputMethodManager::class.java)
            ?.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        paymentViewModel = ViewModelProvider(this).get(PaymentViewModel::class.java)
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        // Inflate the layout for this fragment
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  payment button show the payment
        binding.btnPayment.setOnClickListener {
            openScan()
        }


        cartViewModel.cartLiveData.observe(viewLifecycleOwner, {
            binding.progressBarCartShopping.visibility = View.GONE
            sumAllCartItemsPrice(it)

            binding.rvCartShopping.adapter = CartAdapter(it, this)
            binding.rvCartShopping.layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )

        })

        // change text view text with coupon code
        binding.buttonCode.setOnClickListener {
            val alpha = it.alpha
            val coupon = "style2022"
            val textCode = binding.editCode.text.toString()
            if (alpha == 1f && textCode == coupon) {
                  it.alpha = 0.5f
                    totalCode = (0.83f)
                    binding.textTotal.text = "Total: ${(total * totalCode).toInt()}"
                Toast.makeText(context,"Congratulations you got a 17% discount!",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"Invalid coupon code!",Toast.LENGTH_LONG).show()
            }
            hideKeyboard()
        }




    }

    private fun openScan() {
        val i = Intent(requireActivity(), CardIOActivity::class.java)
        i.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true)
        i.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true)
        i.putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, true)
        i.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false)
        paymentScreen.launch(i)

    }

    private fun sumAllCartItemsPrice(list: List<Cart>) {
        //var total = 0.0
//         sum all the list prices
        total = 0.0
        list.forEach {
            total += it.price.toInt()
        }
        binding.textTotal.text = "Total: ${total.toFloat()}"

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    /// delete product of the card by name
    override fun delete(cart: Cart) {
        cartViewModel.deleteCartByName(cart.name)
        println("Delete: $total}")
    }

    override fun onResume() {
        super.onResume()
        // change the tool bar title
        (requireActivity() as MainActivity).supportActionBar?.title = "Shopping cart"
    }


}
