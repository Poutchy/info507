package td.info507.myapplication.request

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import td.info507.myapplication.model.LinkMessage
import td.info507.myapplication.model.Memory
import td.info507.myapplication.model.Message
import td.info507.myapplication.model.TextMessage
import td.info507.myapplication.storage.MemoryStorage
import td.info507.myapplication.storage.MessageStorage

class MemoryRequest (private val context: Context) {
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
                Toast.makeText(context, "La liste de souvenir a été récupérée avec succès", Toast.LENGTH_SHORT).show() },
            { err ->
                Toast.makeText(context, "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)
        queue.start()
    }

    private fun refresh(json: JSONObject) {
        insert(json)
    }

    private fun insert(json: JSONObject) {
        val memories = json.getJSONArray("memory")
        for (i in 0 until memories.length()) {
            val memory = memories.getJSONObject(i)
            val elems = MemoryStorage.get(context).findAll()

            val newId = if (elems.isNotEmpty()) {
                elems.maxByOrNull { it.id }!!.id + 1
            } else {
                1
            }
            MemoryStorage.get(context).insert(
                Memory(
                    newId,
                    memory.getString(Memory.NAME),
                    memory.getString(Memory.DATE)
                )
            )
            val messages = memory.getJSONArray("messages")
            for (j in 0 until messages.length()) {
                val message = messages.getJSONObject(j)
                val mess = MessageStorage.get(context).findAll()
                val newIdMessage = if (mess.isNotEmpty()) {
                    elems.maxByOrNull { it.id }!!.id + 1
                } else {
                    1
                }
                val type = message.getString("type")
                val retour: Message = if (type == "link") {
                    LinkMessage(
                        newIdMessage,
                        newId,
                        message.getString("text"))
                } else {
                    TextMessage(
                        newIdMessage,
                        newId,
                        message.getString("text"))
                }
                MessageStorage.get(context).insert(
                    retour
                )
            }
        }
    }
}