package dor.rubin.dorproject.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// class nike

@Entity
data class Nike(
    @PrimaryKey
    val nikeId:String =UUID.randomUUID().toString(),
    val nikeCode:String,
    val name: String,
    val image: Int,
    val price: Double,
    val description:String,
    val sizeB:Boolean
)
