package td.info507.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import td.info507.myapplication.R

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        var look: Button = findViewById(R.id.main_menu_look)
        var get: Button = findViewById(R.id.main_menu_get)
        var add: Button = findViewById(R.id.main_menu_add)

        look.setOnClickListener{
            val intent = Intent(applicationContext, ViewMemories::class.java)
            startActivity(intent)
        }

        get.setOnClickListener {
            val intent = Intent(applicationContext, ImportMenu::class.java)
            startActivity(intent)
        }

        add.setOnClickListener {
            val intent = Intent(applicationContext, AddMenu::class.java)
            startActivity(intent)
        }
    }
}