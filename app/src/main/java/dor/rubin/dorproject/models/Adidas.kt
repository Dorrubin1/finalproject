package dor.rubin.dorproject.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// class adidas
@Entity
data class Adidas(
    @PrimaryKey
    val adidasId:String = UUID.randomUUID().toString(),
    val adidasCode:String,
    val name: String,
    val image: Int,
    val price: Double,
    val description:String,
    val sizeB:Boolean
)



