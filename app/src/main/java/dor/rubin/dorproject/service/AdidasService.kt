package dor.rubin.dorproject.service
import dor.rubin.dorproject.models.Adidas
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
// An interface that pulls information from an Internet address
interface AdidasService {
  // format
  @GET("dorrubin1234/Dor-Project/main/Adidass.json")
  suspend fun adidas():List<Adidas>
  companion object{
    fun create():AdidasService{
      return Retrofit.Builder()
              // base URL
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(
          AdidasService::class.java
        )
    }
  }

}