package dor.rubin.dorproject.repository

import dor.rubin.dorproject.models.Adidas
import dor.rubin.dorproject.models.Nike
import dor.rubin.dorproject.service.AdidasService
import dor.rubin.dorproject.service.NikeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NikeRepository {
    companion object{
        suspend fun getNike():List<Nike>{
            // run code on the IO thread
            return withContext(Dispatchers.IO){
                val nike =NikeService.create().nike()
                // return list of nike
                nike
            }
        }
    }
}