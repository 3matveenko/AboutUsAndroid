package kz.fe.about_us_android

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

        var background = findViewById<ImageView>(R.id.about_us_background)
        var regularCustomer = findViewById<ImageButton>(R.id.regular_customer)
        var wholesale = findViewById<ImageButton>(R.id.wholesale)
        var recommended = findViewById<ImageButton>(R.id.recomendet)
        var yandex = findViewById<ImageButton>(R.id.yandex)
        var google = findViewById<ImageButton>(R.id.google)
        var instagram = findViewById<ImageButton>(R.id.instagram)
        var tikTok = findViewById<ImageButton>(R.id.tikTok)
        var youtube = findViewById<ImageButton>(R.id.youtube)
        var twogis = findViewById<ImageButton>(R.id.twogis)

        background.setImageResource(R.drawable.aboutus)

        regularCustomer.setOnClickListener {
             Http(this).send(Click("regularCustomer"))
        }

        wholesale.setOnClickListener {
            Http(this).send(Click("wholesale"))
        }

        recommended.setOnClickListener {
            Http(this).send(Click("recommended"))
        }

        yandex.setOnClickListener {
            Http(this).send(Click("yandex"))
        }

        google.setOnClickListener {
            Http(this).send(Click("google"))
        }

        instagram.setOnClickListener {
            Http(this).send(Click("instagram"))
        }

        tikTok.setOnClickListener {
            Http(this).send(Click("tikTok"))
        }

        youtube.setOnClickListener {
            Http(this).send(Click("youtube"))
        }

        twogis.setOnClickListener {
            Http(this).send(Click("twogis"))
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        var wakeLock = powerManager.newWakeLock(
            PowerManager.SCREEN_BRIGHT_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP,
            "YourApp:WakeLockTag"
        )
        wakeLock?.acquire()


    }
}