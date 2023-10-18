package td.info507.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import td.info507.myapplication.R
import td.info507.myapplication.adapter.MemoryAdapter
import td.info507.myapplication.storage.MemoryStorage

class ViewMemories : AppCompatActivity() {
    private lateinit var list: RecyclerView
    companion object {
        const val MEMORY_ID = "MEMORY_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val mem = MemoryStorage.get(applicationContext).findAll()
        Log.d("totqnksd", mem.size.toString())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_memories)

        list = findViewById(R.id.memory_list)
        list.adapter = object : MemoryAdapter(applicationContext, mem) {
            override fun onItemClick(view: View) {
                val intent = Intent(applicationContext, ViewMemory::class.java).apply {
                    putExtra(MEMORY_ID, view.tag as Int)
                }
                startActivity(intent)
            }

            override fun onLongItemClick(view: View): Boolean {
                TODO("Not yet implemented")
            }
        }
        list.layoutManager = LinearLayoutManager(this)
        list.adapter?.notifyDataSetChanged()

        var quit: Button = findViewById(R.id.memory_list_quit)
        var add: Button = findViewById(R.id.memory_list_add)

        add.setOnClickListener {
            val intent = Intent(applicationContext, AddMenu::class.java)
            startActivity(intent)
        }
    }
}