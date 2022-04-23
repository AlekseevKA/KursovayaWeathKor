package com.example.weathkor

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

       // var SplashScreenImage = findViewById(R.id.SplashScreenImage) as ImageView
       // SplashScreenImage.animate().apply {
            //круг вокруг себя
           // duration = 2000
           // rotationYBy(180f)
            //перемещение от точки до точки(можнно добавить облака)
            val valueAnimator = ValueAnimator.ofFloat(0f, -1060f)
            valueAnimator.addUpdateListener {

          //      val value = it.animatedValue as Float

            //    SplashScreenImage.translationY = value
           // }
           // valueAnimator.interpolator = LinearInterpolator()
           // valueAnimator.duration = 2000
           // valueAnimator.start()

        }
        var textView = findViewById(R.id.AppName) as TextView
        textView.animate().apply {

            //перемещение от точки до точки(можнно добавить облака)
           val valueAnimator = ValueAnimator.ofFloat(0f, -880f)
            valueAnimator.addUpdateListener {

                val value = it.animatedValue as Float

                textView.translationY = value
            }
            valueAnimator.interpolator = LinearInterpolator()
            valueAnimator.duration = 2000
          //  valueAnimator.start()

        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)


    }
}