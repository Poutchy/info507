package td.info507.myapplication.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import td.info507.myapplication.R
import td.info507.myapplication.adapter.MemoryAdapter
import td.info507.myapplication.storage.MemoryStorage

class ViewMemories : AppCompatActivity() {
    private lateinit var list: RecyclerView
    private lateinit var adapter: MemoryAdapter
    companion object {
        const val MEMORY_ID = "MEMORY_ID"
    }

    private fun initAdapter() {
        val mem = MemoryStorage.get(applicationContext).findAll()
        adapter = object : MemoryAdapter(mem) {
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
        list.adapter = adapter
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_memories)

        list = findViewById(R.id.memory_list)
        initAdapter()

        list.layoutManager = LinearLayoutManager(this)

        val add: Button = findViewById(R.id.memory_list_add)

        add.setOnClickListener {
            intent = Intent(applicationContext, AddMenu::class.java)
            setResult(Activity.RESULT_OK, intent)
            startForResult.launch(intent)
        }
    }

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            initAdapter()
            list.adapter!!.notifyItemInserted(adapter.itemCount)
        }
    }
}