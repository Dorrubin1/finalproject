package dor.rubin.dorproject.ui.nike

import android.app.Application
import androidx.lifecycle.*
import dor.rubin.dorproject.database.ClothesDatabase
import dor.rubin.dorproject.models.Nike
import dor.rubin.dorproject.service.NikeService
import kotlinx.coroutines.launch
import java.util.*

class NikeViewModel(application: Application) : AndroidViewModel(application) {


    var liveNike: LiveData<List<Nike>>
    private val dbNike: ClothesDatabase = ClothesDatabase.create(getApplication())

    init {
        //async code fetch the nike and update the live data
        val db = ClothesDatabase.create(application)
        liveNike = db.nikeDao().getLiveNike()
        viewModelScope.launch {
            addNike()
        }
    }

    // add nike to room data base
    // if there is no internet the information is taken from room data base
    private suspend fun addNike() {
        with(dbNike.nikeDao()) {
            var exist = getNike()
            var delReset = true
            if (exist.isEmpty()) {
                println("Empty")
                delReset = false
                exist = NikeService.create().nike()
            }
            for (nikeDB in exist) {
                if (delReset && exist.find { it.nikeCode == nikeDB.nikeCode } != null) {
                    continue
                }
                val nikeId = UUID.randomUUID().toString()
                add(
                    Nike(
                        nikeId, nikeDB.nikeCode, nikeDB.name,
                        nikeDB.image, nikeDB.price, nikeDB.description, nikeDB.sizeB
                    )
                )
            }

        }
    }
}

