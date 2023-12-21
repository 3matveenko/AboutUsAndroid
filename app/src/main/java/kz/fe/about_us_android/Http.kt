package kz.fe.about_us_android

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier

@SuppressLint("SuspiciousIndentation")
class Http(private var activity: AppCompatActivity) {
    private lateinit var api: API

    private var gson: Gson = GsonBuilder()
        .setLenient()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .excludeFieldsWithModifiers(
            Modifier.STATIC,
            Modifier.TRANSIENT,
            Modifier.VOLATILE
        )
        .create()

    init {
        var flag = false
        val server: String = "http://192.168.0.156:28081/"
        Log.d("courier_log", "retrofit init $server")
        val retrofit = server.let {
            Retrofit.Builder()
                .baseUrl(it)
                .addConverterFactory(GsonConverterFactory.create(this.gson))
                .build()
        }
        if (retrofit != null) {
            this.api = retrofit.create(API::class.java)
        }
    }

    fun send(click: Click) {
        this.api.click(
            click
        ).enqueue(object : Callback<String> {
            @SuppressLint("CutPasteId", "SetTextI18n", "SimpleDateFormat")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.code() == 200) {
                    Toast.makeText(activity, "Спасибо за отзыв", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(activity, response.code().toString(), Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call<String>, throwable: Throwable) {
                throwable.localizedMessage?.let { Log.e("httpConnect", it) }
                Log.e("httpConnect", "Ошибка при подключении: ${throwable.toString()}")
                Toast.makeText(activity, "Ошибка подключения", Toast.LENGTH_LONG).show()

            }
        })
    }
}