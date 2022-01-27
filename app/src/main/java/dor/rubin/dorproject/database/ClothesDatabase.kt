package dor.rubin.dorproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dor.rubin.dorproject.dao.AdidasDao
import dor.rubin.dorproject.dao.ConverseDao
import dor.rubin.dorproject.dao.NikeDao
import dor.rubin.dorproject.models.Adidas
import dor.rubin.dorproject.models.Converse
import dor.rubin.dorproject.models.Nike

// version
const val DB_VERSION = 3

// name data base
const val DB_NAME = "ClothesDatabase"

@Database(entities = [Nike::class,Adidas::class,Converse::class], version = DB_VERSION)
abstract class ClothesDatabase : RoomDatabase() {
    companion object {
        fun create(context: Context): ClothesDatabase {
            return Room.databaseBuilder(context, ClothesDatabase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    //get the dao object
    abstract fun nikeDao():NikeDao
    abstract fun adidasDao() :AdidasDao
    abstract fun converseDao():ConverseDao
}