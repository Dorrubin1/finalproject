package dor.rubin.dorproject.dao

import android.app.Person
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

import androidx.room.Query
import dor.rubin.dorproject.models.Converse
import dor.rubin.dorproject.models.Nike
@Dao
interface NikeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(item: Nike)
    @Query("SELECT * FROM Nike")
    fun getLiveNike():LiveData<List<Nike>>

    @Query("SELECT * FROM Nike")
    suspend  fun getNike():List<Nike>
}