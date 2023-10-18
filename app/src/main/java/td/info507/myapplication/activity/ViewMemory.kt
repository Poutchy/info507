package td.info507.myapplication.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Images.Media
import android.util.Base64
import android.util.Log
import android.util.Size
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import td.info507.myapplication.R
import td.info507.myapplication.adapter.MessageAdapter
import td.info507.myapplication.model.ImageMessage
import td.info507.myapplication.model.LinkMessage
import td.info507.myapplication.model.Message
import td.info507.myapplication.model.TextMessage
import td.info507.myapplication.storage.MessageStorage
import java.io.ByteArrayOutputStream

class ViewMemory : AppCompatActivity() {
    private lateinit var list: RecyclerView
    private lateinit var adapter: MessageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_memory)

        Log.d("ON CREATE ACT MEM","ON CREATE ACT MEM")

        list = findViewById(R.id.message_list)
        adapter = object : MessageAdapter((MessageStorage.get(applicationContext).findAllById(intent.getIntExtra(ViewMemories.MEMORY_ID, 0)) as ArrayList<Message>), applicationContext) {
            override fun onItemClick(view: View) {
            }

            override fun onLongItemClick(view: View): Boolean {
                return true
            }
        }
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this)

        var creation_link: ImageButton = findViewById(R.id.creation_message_link)
        val creation_text: EditText = findViewById(R.id.creation_message_text)
        val creation_image: ImageButton = findViewById(R.id.creation_message_image)
        val send: ImageButton = findViewById(R.id.creation_message_send)

        creation_image.setOnClickListener {
            selectImage()
        }
        send.setOnClickListener {
            sendText(creation_text.text.toString())
            creation_text.text.clear()
        }
        creation_link.setOnClickListener {
            sendLink(creation_text.text.toString())
            creation_text.text.clear()
        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI)
        resultImageLauncher.launch(intent)
    }

    private var resultImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {res ->
        if (res.resultCode == Activity.RESULT_OK) {
            val uri: Uri = res.data!!.data!!
            val bitmap = if (Build.VERSION.SDK_INT >= 29) {
                contentResolver.loadThumbnail(
                    uri,
                    Size(400, 400),
                    null
                )
            } else {
                Bitmap.createScaledBitmap(
                    Media.getBitmap(
                        contentResolver,
                        uri
                    ), 400, 400, false
                )
            }
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val b = baos.toByteArray()
            val code = Base64.encodeToString(b, Base64.DEFAULT)
            addMessage(ImageMessage(0, intent.getIntExtra(ViewMemories.MEMORY_ID, 0), code))
        }
    }

    private fun sendText(s: String) {
        if (s.isNotBlank()) {
            addMessage(TextMessage(0, intent.getIntExtra(ViewMemories.MEMORY_ID, 0), s))
        }
    }

    private fun addMessage (m: Message) {
        val elems = MessageStorage.get(applicationContext).findAll()

        val newId = if (elems.isNotEmpty()) {
            elems.maxByOrNull { it.id }!!.id + 1
        } else {
            1
        }
        m.id = newId
        MessageStorage.get(applicationContext).insert(m)
        adapter.messages.add(m)
        Log.d("ALL MESSAGES", adapter.messages.toString())
        list.adapter?.notifyItemInserted(adapter.messages.size -1)
    }

    private fun sendLink(s: String) {
        if (s.isNotBlank()) {
            addMessage(LinkMessage(0, intent.getIntExtra(ViewMemories.MEMORY_ID, 0), s))
        }
    }
}