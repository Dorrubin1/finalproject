package dor.rubin.dorproject.repository

import dor.rubin.dorproject.models.Converse
import dor.rubin.dorproject.service.ConverseService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConverseRepository {
    companion object{
        suspend fun getConverse():List<Converse>{
            // run the code on the IO thread
            return withContext(Dispatchers.IO){
                val converse = ConverseService.create().converse()
                // return list of converse
                converse
            }
        }
    }
}