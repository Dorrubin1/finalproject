package dor.rubin.dorproject

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.MediaController
import android.widget.VideoView

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    var videoView: VideoView? = null
    var mediaController: MediaController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        videoView = findViewById(R.id.video_splash_screen)
        if (mediaController == null) {
            mediaController = MediaController(this)
        }
        videoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.collection))
        videoView!!.requestFocus()
        videoView!!.start()
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },3000) //3 seconds
    }
}