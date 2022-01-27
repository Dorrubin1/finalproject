package dor.rubin.dorproject.ui.adidas

import android.app.Application
import androidx.lifecycle.*
import dor.rubin.dorproject.database.ClothesDatabase
import dor.rubin.dorproject.models.Adidas

import dor.rubin.dorproject.service.AdidasService

import kotlinx.coroutines.launch
import java.util.*

class AdidasViewModel(application: Application) : AndroidViewModel(application) {
    // live data:
    var liveAdidas: LiveData<List<Adidas>>
    private val dbAdidas: ClothesDatabase = ClothesDatabase.create(getApplication())



    //async code fetch the adidas and update the live data
    init {
        val db = ClothesDatabase.create(application)
        liveAdidas = db.adidasDao().getLiveAdidas()
        viewModelScope.launch {
            addAdidas()
        }
    }

    // if there is no internet the information is taken from room data base
    private suspend fun addAdidas() {

        with(dbAdidas.adidasDao()) {
            var exist = getAdidas()
            var delReset = true
            if (exist.isEmpty()) {
                println("Empty")
                delReset = false
                exist = AdidasService.create().adidas()
            }
            for (adidasDB in exist) {
                if (delReset && exist.find { it.adidasCode == adidasDB.adidasCode } != null) {
                    continue
                }
                val adidasId = UUID.randomUUID().toString()
                add(
                    Adidas(
                        adidasId,
                        adidasDB.adidasCode, adidasDB.name, adidasDB.image,
                        adidasDB.price, adidasDB.description, adidasDB.sizeB
                    )
                )

            }
        }

    }


}