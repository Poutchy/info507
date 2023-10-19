package td.info507.myapplication.activity

import android.app.Activity
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.myapplication.R
import td.info507.myapplication.model.Memory
import td.info507.myapplication.storage.MemoryStorage
import java.util.Locale


class AddMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_menu)

        val date: TextView = findViewById(R.id.creation_date)
        val dateF = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())

        date.text = dateF.format(Calendar.getInstance().time)

        val name: EditText = findViewById(R.id.creation_name)

        val cancel: FloatingActionButton = findViewById(R.id.creation_cancel_button)
        val accept: FloatingActionButton = findViewById(R.id.creation_accept_button)

        accept.setOnClickListener {
            create_memory(date.text.toString(), name.text.toString())
        }
        cancel.setOnClickListener {
            finish()
        }
    }

    private fun create_memory(d: String, n: String) {
        val elems = MemoryStorage.get(applicationContext).findAll()

        val newId = if (elems.isNotEmpty()) {
            elems.maxByOrNull { it.id }!!.id + 1
        } else {
            1
        }

        MemoryStorage.get(applicationContext).insert(Memory(newId, n, d))
        setResult(Activity.RESULT_OK)
        finish()
    }
}