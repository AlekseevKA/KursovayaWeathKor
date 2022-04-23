package com.example.weathkor

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var button: Button
    lateinit var cityName: String

    val API: String = "5f6f086fd30a86a94be1d1559f602782"
    //val CITY: String = "Yakutsk"
    //val API: String = "e14808757fdb62ed85095ae38fbd9393" // апишка здесь
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        editText = findViewById(R.id.editText)
        button.setOnClickListener {
            cityName = editText.text.toString()
            weatherTask().execute()
        }
        weatherTask().execute()
    }
    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }
        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$cityName&units=metric&appid=$API").readText(
                    Charsets.UTF_8
                )
            }catch (e: Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.ENGLISH).format(Date(updatedAt*1000))
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
                val tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
                val pressure = main.getString("pressure")+"мм рт. ст."
                val humidity = main.getString("humidity")+"%"

                val sunrise:Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")+"м/с"
                val weatherDescription = weather.getString("description")

                //val citaty = weather.getString("citaty")

                val address = jsonObj.getString("name")+", "+sys.getString("country")

                /* Заполнение  */
                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updated_at).text =  updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.temp_max).text = tempMax
                findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("HH:mm", Locale.ENGLISH).format(Date(sunrise*1000))
                findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("hh:mm", Locale.ENGLISH).format(Date(sunset*1000))
                findViewById<TextView>(R.id.wind).text = windSpeed
                findViewById<TextView>(R.id.pressure).text = pressure
                findViewById<TextView>(R.id.humidity).text = humidity

                //массив цитат
                val citata = listOf("В отвратную погоду, в холодный дождь и снег - становятся приятней кофе, кот и плед.",
                    "Если бы погода не менялась, тем для разговоров было бы в 2 раза меньше",
                    "Демо-версия хорошей погоды закончилась, приобретите лицензию!",
                    "Не бывает плохой погоды, бывает плохое настроение" ,
                    "Синоптики ошибаются лишь один раз. Но каждый день.","Точный прогноз погоды на завтра вы узнаете послезавтра.",
                    "Любая погода хорошая, если есть с кем настроением хорошим делиться.")
                val rnds = (citata).random()//рандомный выбор
                findViewById<TextView>(R.id.citaty).text = rnds//вывод

                /* Отображение основного дизайна */
                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE

            } catch (e: Exception) {

            }

        }
    }
}