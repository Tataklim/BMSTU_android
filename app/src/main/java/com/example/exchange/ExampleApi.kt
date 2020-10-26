//package com.example.exchange
//
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.http.GET
//
//private val API_URL = "https://min-api.cryptocompare.com/"
//private val API_URL2 = "https://min-api.cryptocompare.com/data/symbol/histoday?fsym=BTC&tsym=USD&limit=10"
//
//val moshi = Moshi.Builder()
//    .addLast(KotlinJsonAdapterFactory())
//    .build()
//
//val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .baseUrl(API_URL)
//    .build()
//
//interface ExampleApiService {
//    @GET("{\"Response\":\"Success\",\"Message\":\"\",\"HasWarning\":false,\"Type\":100,\"RateLimit\":{},\"Data\":{\"Aggregated\":false,\"TimeFrom\":1602633600,\"TimeTo\":1603497600,\"Data\":" +
//            "[{\"time\":1602633600,\"high\":11550.25,\"low\":11298.88,\"open\":11427.29,\"volumefrom\":22758.2,\"volumeto\":259711904.03,\"close\":11429.3,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"}," +
//            "{\"time\":1602720000,\"high\":11607.59,\"low\":11269.05,\"open\":11429.3,\"volumefrom\":33044.49,\"volumeto\":377801262.9,\"close\":11508.31,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"}," +
//            "{\"time\":1602806400,\"high\":11545.35,\"low\":11224.89,\"open\":11508.31,\"volumefrom\":29300.16,\"volumeto\":332320579.31,\"close\":11326.25,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"}," +
//            "{\"time\":1602892800,\"high\":11410.01,\"low\":11270.69,\"open\":11326.25,\"volumefrom\":9901.25,\"volumeto\":112306061.3,\"close\":11367.65,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"}," +
//            "{\"time\":1602979200,\"high\":11513.68,\"low\":11357.25,\"open\":11367.65,\"volumefrom\":11528.41,\"volumeto\":131886570.58,\"close\":11513.33,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"}," +
//            "{\"time\":1603065600,\"high\":11821.95,\"low\":11417.11,\"open\":11513.33,\"volumefrom\":39224.67,\"volumeto\":456404609.75,\"close\":11756.88,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"}," +
//            "{\"time\":1603152000,\"high\":12047.73,\"low\":11688.39,\"open\":11756.88,\"volumefrom\":48504.61,\"volumeto\":576128149.71,\"close\":11921.78,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"}," +
//            "{\"time\":1603238400,\"high\":13236.39,\"low\":11900.51,\"open\":11921.78,\"volumefrom\":103080.18,\"volumeto\":1296152945.69,\"close\":12813.11,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"}," +
//            "{\"time\":1603324800,\"high\":13197.69,\"low\":12700.74,\"open\":12813.11,\"volumefrom\":57124.85,\"volumeto\":739591002.3,\"close\":12990.25,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"}," +
//            "{\"time\":1603411200,\"high\":13031.82,\"low\":12732.62,\"open\":12990.25,\"volumefrom\":32269.84,\"volumeto\":416662855.54,\"close\":12937.2,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"}," +
//            "{\"time\":1603497600,\"high\":12941.74,\"low\":12912.4,\"open\":12937.2,\"volumefrom\":284.95,\"volumeto\":3681194.19,\"close\":12915.53,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"}]}}")
////    suspend fun getData(): List<ExampleData>
//    fun getData(): List<ExampleData>
//}
//
//object ExampleApi {
//    val retrofitService: ExampleApiService by lazy {
//        retrofit.create(ExampleApiService::class.java)
//    }
//}