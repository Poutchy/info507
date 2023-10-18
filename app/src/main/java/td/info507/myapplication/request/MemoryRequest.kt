package td.info507.myapplication.request

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import td.info507.myapplication.model.Memory
import td.info507.myapplication.model.Updatable
import td.info507.myapplication.storage.MemoryStorage

class MemoryRequest (private val context: Context, updatable: Updatable) {
    companion object {
        private const val URL = "http://51.68.91.213/gr-1-2/memories.json"
    }

    init {
        val queue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(
            Request.Method.GET,
            URL,
            null,
            { res ->
                refresh(res)
                updatable.update()
                Toast.makeText(context, "La liste de souvenir a été récupérée avec succès", Toast.LENGTH_SHORT).show() },
            { err ->
                Toast.makeText(context, "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)
        queue.start()
    }

    private fun refresh(json: JSONObject) {
        delete()
        insert(json)
    }

    private fun delete() {
        for (memory in MemoryStorage.get(context).findAll()) {
            MemoryStorage.get(context).delete(memory.id)
        }
    }

    private fun insert(json: JSONObject) {
        val memories = json.getJSONArray("memory")
        for (i in 0 until memories.length()) {
            val memory = memories.getJSONObject(i)
            MemoryStorage.get(context).insert(
                Memory(
                    i,
                    memory.getString(Memory.NAME),
                    memory.getString(Memory.DATE)
                )
            )
        }
    }
}