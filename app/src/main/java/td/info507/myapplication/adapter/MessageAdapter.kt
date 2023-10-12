package td.info507.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import td.info507.myapplication.R
import td.info507.myapplication.storage.MessageStorage

abstract class MessageAdapter(private val context: Context): RecyclerView.Adapter<MessageAdapter.MessageHolder>() {
    class MessageHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val content: TextView = itemView.findViewById(R.id.message_content)
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
        val message = MessageStorage.get(context).findAll().get(position)
        holder.itemView.tag = message.id
        holder.content.text = message.content
    }

    override fun getItemCount(): Int {
        return MessageStorage.get(context).size()
    }
}
