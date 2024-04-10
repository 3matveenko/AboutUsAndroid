package kz.fe.about_us_android

import android.annotation.SuppressLint
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
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
                    activity.findViewById<ImageView>(R.id.about_us_background).setImageResource(R.drawable.ok)
                    activity.findViewById<ImageButton>(R.id.regular_customer).visibility = View.GONE
                    activity.findViewById<ImageButton>(R.id.wholesale).visibility = View.GONE
                    activity.findViewById<ImageButton>(R.id.recomendet).visibility = View.GONE
                    activity.findViewById<ImageButton>(R.id.yandex).visibility = View.GONE
                    activity.findViewById<ImageButton>(R.id.google).visibility = View.GONE
                    activity.findViewById<ImageButton>(R.id.instagram).visibility = View.GONE
                    activity.findViewById<ImageButton>(R.id.tikTok).visibility = View.GONE
                    activity.findViewById<ImageButton>(R.id.youtube).visibility = View.GONE
                    activity.findViewById<ImageButton>(R.id.twogis).visibility = View.GONE

                    Handler().postDelayed({
                        activity.findViewById<ImageView>(R.id.about_us_background).visibility = View.VISIBLE
                        activity.findViewById<ImageView>(R.id.about_us_background).setImageResource(R.drawable.aboutus)
                        activity.findViewById<ImageButton>(R.id.regular_customer).visibility = View.VISIBLE
                        activity.findViewById<ImageButton>(R.id.wholesale).visibility = View.VISIBLE
                        activity.findViewById<ImageButton>(R.id.recomendet).visibility = View.VISIBLE
                        activity.findViewById<ImageButton>(R.id.yandex).visibility = View.VISIBLE
                        activity.findViewById<ImageButton>(R.id.google).visibility = View.VISIBLE
                        activity.findViewById<ImageButton>(R.id.instagram).visibility = View.VISIBLE
                        activity.findViewById<ImageButton>(R.id.tikTok).visibility = View.VISIBLE
                        activity.findViewById<ImageButton>(R.id.youtube).visibility = View.VISIBLE
                        activity.findViewById<ImageButton>(R.id.twogis).visibility = View.VISIBLE
                    }, 3000)
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