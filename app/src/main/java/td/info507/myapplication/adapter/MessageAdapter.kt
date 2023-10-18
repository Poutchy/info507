package td.info507.myapplication.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import td.info507.myapplication.R
import td.info507.myapplication.model.Message
import td.info507.myapplication.storage.MessageStorage

abstract class MessageAdapter(
    var messages: ArrayList<Message>,
    private val context: Context
): RecyclerView.Adapter<MessageAdapter.MessageHolder>() {
    class MessageHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val content: TextView = itemView.findViewById(R.id.message_content)
        val image: ImageView = itemView.findViewById(R.id.message_image)
        val link: TextView = itemView.findViewById(R.id.message_link)
    }

    abstract fun onItemClick(view: View)
    abstract fun onLongItemClick(view: View): Boolean

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        view.setOnClickListener { view -> onItemClick(view) }
        view.setOnLongClickListener { view -> onLongItemClick(view) }
        return MessageHolder(view)
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        val message = messages[position]
        holder.itemView.tag = message.id
        when (message.type) {
            "image" -> {
                Log.d("TEST", message.content)
                val bytes = Base64.decode(message.content, 0)
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                holder.image.setImageBitmap(bitmap)
            }
            "link" -> {
                holder.link.text = message.content
            }
            else -> holder.content.text = message.content
        }
    }

    override fun getItemCount(): Int {
        Log.d("TOT MESS ADAPT", messages.size.toString())
        return messages.size
    }
}
