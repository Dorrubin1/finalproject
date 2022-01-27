package dor.rubin.dorproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dor.rubin.dorproject.databinding.CartItemBinding

import dor.rubin.dorproject.models.Cart

// interface for delete product from the card
interface CartItemClickedListener {

    fun delete(cart:Cart)
}

class CartAdapter(private val cart: List<Cart>,private val listener:CartItemClickedListener) : RecyclerView.Adapter<CartAdapter.VH>() {
    inner class VH(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val cName: TextView = binding.textNameCart
        private val cPrice: TextView = binding.textPriceCart
        private val cSize: TextView = binding.textSizeCart
        //The information displayed in each detail
        fun bind(cart: Cart) {
            binding.imageCart.setImageResource(cart.image)
            cName.text = cart.name
            cPrice.text = cart.price.toString()
            cSize.text = cart.size
            binding.btnDelete.setOnClickListener {
                listener.delete(cart)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val cartShopping = cart[position]
        holder.bind(cartShopping)


    }

    override fun getItemCount(): Int = cart.size

}