package dor.rubin.dorproject.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dor.rubin.dorproject.models.Adidas
import dor.rubin.dorproject.models.Converse


@Dao
interface ConverseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(converse: Converse)

    @Query("SELECT * FROM Converse")
    fun getLiveConverse():LiveData<List<Converse>>

    @Query("SELECT * FROM Converse")
    suspend fun getConverse():List<Converse>
}