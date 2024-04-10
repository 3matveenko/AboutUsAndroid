package kz.fe.about_us_android

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface API {


    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("/rest/click")
    fun click(@Body query: Click): retrofit2.Call<String>



}