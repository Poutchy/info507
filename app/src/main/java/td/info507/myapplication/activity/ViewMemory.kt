package td.info507.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import td.info507.myapplication.R
import td.info507.myapplication.adapter.MessageAdapter

class ViewMemory : AppCompatActivity() {
    private lateinit var list: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_memory)

        list = findViewById(R.id.message_list)
        list.adapter = object : MessageAdapter(applicationContext) {
            override fun onItemClick(view: View) {
            }

            override fun onLongItemClick(view: View): Boolean {
                return true
            }

        }
    }
}