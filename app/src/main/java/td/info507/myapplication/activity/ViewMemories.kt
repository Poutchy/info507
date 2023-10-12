package td.info507.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import td.info507.myapplication.R
import td.info507.myapplication.adapter.MemoryAdapter
import td.info507.myapplication.storage.MemoryStorage

class ViewMemories : AppCompatActivity() {
    private lateinit var list: RecyclerView
    private lateinit var refreshLayout: SwipeRefreshLayout
    companion object {
        const val MEMORY_ID = "MEMORY_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_memories)

        list = findViewById(R.id.memory_list)
        list.adapter = object : MemoryAdapter(applicationContext) {
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
    }
}