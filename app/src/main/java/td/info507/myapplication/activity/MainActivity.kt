package td.info507.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import td.info507.myapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun goToMAinMenu() {
            val intent = Intent(applicationContext, MainMenu::class.java)
            startActivity(intent)
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            goToMAinMenu()
        }
    }
}