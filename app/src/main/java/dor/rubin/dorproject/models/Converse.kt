package dor.rubin.dorproject.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// class converse

@Entity
data class Converse(
    @PrimaryKey
    val converseId: String = UUID.randomUUID().toString(),
    val converseCode: String,
    val name: String,
    val image: Int,
    val price: Double,
    val description: String,
    val sizeB: Boolean
)