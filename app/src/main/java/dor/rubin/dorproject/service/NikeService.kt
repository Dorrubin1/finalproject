package dor.rubin.dorproject.service
import dor.rubin.dorproject.models.Adidas
import dor.rubin.dorproject.models.Nike
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
// An interface that pulls information from an Internet address
interface NikeService {
  // format
  @GET("dorrubin1234/Dor-Project/main/Nikes.json")
  suspend fun nike():List<Nike>
  companion object{
    fun create():NikeService{
      return Retrofit.Builder()
              // base URL
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(
          NikeService::class.java
        )
    }
  }

}