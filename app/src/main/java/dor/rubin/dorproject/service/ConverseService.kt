package dor.rubin.dorproject.service
import dor.rubin.dorproject.models.Adidas
import dor.rubin.dorproject.models.Converse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
// An interface that pulls information from an Internet address
interface ConverseService {
  // format
  @GET("dorrubin1234/Dor-Project/main/Converses.json")
  suspend fun converse():List<Converse>
  companion object{
    fun create():ConverseService{
      return Retrofit.Builder()
              // base URL
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(
          ConverseService::class.java
        )
    }
  }

}