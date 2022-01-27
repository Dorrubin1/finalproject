package dor.rubin.dorproject.repository

import dor.rubin.dorproject.models.Adidas
import dor.rubin.dorproject.service.AdidasService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdidasRepository {
    companion object{
        suspend fun getAdidas():List<Adidas>{
            // run the code on the IO thread
            return withContext(Dispatchers.IO){
                val adidas =AdidasService.create().adidas()
                // return list of adidas
                adidas
            }
        }
    }
}