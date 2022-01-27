package dor.rubin.dorproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import dor.rubin.dorproject.databinding.AdidasItemBinding
import dor.rubin.dorproject.models.Adidas

import dor.rubin.dorproject.ui.adidas.AdidasFragmentDirections

class AdidasAdapter(val adidas: List<Adidas>) : RecyclerView.Adapter<AdidasAdapter.VH>() {
    //object for a  single Adidas item :
    class VH(val binding: AdidasItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        // return VH
        return VH(
            AdidasItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    // Given a position in the list - and given the design object of Adidas
    override fun onBindViewHolder(holder: VH, position: Int) {
        val adidas = adidas[position]
        holder.binding.apply {
            textNameAdidas.text = adidas.name
//            textPriceAdidas.text = "Price: ₪ ${adidas.price}"
            imageAdidas.setImageResource(adidas.image)

            imageAdidas.setOnClickListener {
                //Transmission of information through an argument
                val action =
                    AdidasFragmentDirections
                        .actionNavSlideshowToShoesInfo(adidas.adidasCode
                            ,adidas.name,adidas.price.toFloat()
                            ,adidas.description,adidas.image,adidas.sizeB
                           )
                it.findNavController().navigate(action)
            }

        }
    }
     // list adidas size
    override fun getItemCount(): Int = adidas.size


}