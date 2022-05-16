package personal.udacity.damc.com.cooperativeexpenses.expenses.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import personal.udacity.damc.com.cooperativeexpenses.expenses.network.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(
        MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

interface NetworkEmailValidation {

    @GET("/email")
    suspend fun checkIfIsFormatAsEmail(@Query("email") email: String): EmailValidator

}

object NasaAPI {
    val retrofitService : NetworkEmailValidation by lazy { retrofit.create(NetworkEmailValidation::class.java) }
}

object Constants {
    const val BASE_URL = "https://api.eva.pingutil.com"
}