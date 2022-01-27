package dor.rubin.dorproject.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dor.rubin.dorproject.models.Adidas


@Dao
interface AdidasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(adidas: Adidas)
    @Query("SELECT * FROM Adidas")
    fun getLiveAdidas():LiveData<List<Adidas>>

    @Query("SELECT * FROM Adidas")
    fun getAdidas():List<Adidas>
}