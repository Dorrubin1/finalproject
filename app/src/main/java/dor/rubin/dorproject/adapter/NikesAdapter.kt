package dor.rubin.dorproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import dor.rubin.dorproject.databinding.NikeItemBinding
import dor.rubin.dorproject.models.Nike

import dor.rubin.dorproject.ui.nike.NikeFragmentDirections

class NikesAdapter(private val nikes: List<Nike>) : RecyclerView.Adapter<NikesAdapter.VH>() {
    //object for a  single Nike item :
    class VH(val binding: NikeItemBinding) : RecyclerView.ViewHolder(binding.root)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            NikeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    // Given a position in the list - and given the design object of Nike
    override fun onBindViewHolder(holder: VH, position: Int) {
        val nike = nikes[position]
        holder.binding.apply {
            textName.text = nike.name
//            textPriceNike.text = "Price: ₪ ${nike.price}"
            imageNike.setImageResource(nike.image)
            imageNike.setOnClickListener {
                //Transmission of information through an argument
                val action = NikeFragmentDirections.
                actionNavGalleryToShoesInfo(nike.nikeCode
                    ,nike.name,nike.price.toFloat(), nike.description,nike.image,nike.sizeB
                )
                it.findNavController().navigate(action)
            }


        }
    }
    // list nike size
    override fun getItemCount(): Int = nikes.size


}