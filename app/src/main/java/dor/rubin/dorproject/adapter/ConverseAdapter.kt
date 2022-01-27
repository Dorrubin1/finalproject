package dor.rubin.dorproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import dor.rubin.dorproject.databinding.AdidasItemBinding
import dor.rubin.dorproject.databinding.ConverseItemBinding
import dor.rubin.dorproject.databinding.NikeItemBinding
import dor.rubin.dorproject.models.Adidas
import dor.rubin.dorproject.models.Converse

import dor.rubin.dorproject.models.Nike
import dor.rubin.dorproject.ui.converse.ConverseFragmentDirections




class ConverseAdapter(val converse: List<Converse>) : RecyclerView.Adapter<ConverseAdapter.VH>() {
    //object for a  single Converse item :
    class VH(val binding: ConverseItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ConverseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    // Given a position in the list - and given the design object of Converse
    override fun onBindViewHolder(holder: VH, position: Int) {
        val converse = converse[position]
        holder.binding.apply {
            textNameConverse.text = converse.name
            imageConverse.setImageResource(converse.image)
            imageConverse.setOnClickListener {
                //Transmission of information through an argument
                val action =
                    ConverseFragmentDirections
                        .actionNavConverseToShoesInfo(converse.converseCode,
                        converse.name,
                            converse.price.toFloat(),
                            converse.description,converse.image,converse.sizeB)
                it.findNavController().navigate(action)


            }
        }
    }
   //  list converse size
    override fun getItemCount(): Int = converse.size


}