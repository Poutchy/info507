package td.info507.myapplication.activity

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.myapplication.R
import java.util.Locale


class AddMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_menu)

        var date: TextView = findViewById(R.id.creation_date)
        val dateF = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())

        date.text = dateF.format(Calendar.getInstance().time)

        var name: EditText = findViewById(R.id.creation_name)

        var cancel: FloatingActionButton = findViewById(R.id.creation_cancel_button)
        var accept: FloatingActionButton = findViewById(R.id.creation_accept_button)
    }
}