package dor.rubin.dorproject.ui.converse

import android.app.Application
import androidx.lifecycle.*
import dor.rubin.dorproject.database.ClothesDatabase
import dor.rubin.dorproject.models.Converse
import dor.rubin.dorproject.repository.ConverseRepository
import dor.rubin.dorproject.service.ConverseService
import kotlinx.coroutines.launch
import java.util.*

class ConverseViewModel(application: Application) : AndroidViewModel(application) {
    var converseLive:LiveData<List<Converse>>
    private val dbConverse :ClothesDatabase = ClothesDatabase.create(getApplication())


    init {
        //async code fetch the converse and update the live data
        val db = ClothesDatabase.create(getApplication())
        converseLive = db.converseDao().getLiveConverse()
        viewModelScope.launch {
           addConverse()
        }
    }


    // if there is no internet the information is taken from room data base
    private suspend fun addConverse() {
        with(dbConverse.converseDao()){
            var exist = getConverse()
            var delReset = true
                if(exist.isEmpty()) {
                    println("Empty")
                    delReset = false
                    exist = ConverseService.create().converse()
                }
              for (converseDB in exist) {
                if(delReset && exist.find { it.converseCode == converseDB.converseCode }!=null) {
                    continue
                }
                val converseId = UUID.randomUUID().toString()
                add(Converse(
                    converseId,converseDB.converseCode,
                    converseDB.name,converseDB.image,
                    converseDB.price,
                    converseDB.description,
                    converseDB.sizeB
                ))
            }
        }

    }


}