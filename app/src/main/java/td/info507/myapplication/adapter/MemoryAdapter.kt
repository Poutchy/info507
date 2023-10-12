package td.info507.myapplication.adapter

import td.info507.myapplication.R
import td.info507.myapplication.storage.MemoryStorage
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

abstract class MemoryAdapter(private val context: Context) : RecyclerView.Adapter<MemoryAdapter.MemoryHolder>() {

    class MemoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.memory_name_list)
        val date: TextView = itemView.findViewById(R.id.memory_date_list)
    }

    abstract fun onItemClick(view: View)
    abstract fun onLongItemClick(view: View): Boolean

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_memory, parent, false)
        view.setOnClickListener { view -> onItemClick(view) }
        view.setOnLongClickListener { view -> onLongItemClick(view) }
        return MemoryHolder(view)
    }

    override fun onBindViewHolder(holder: MemoryHolder, position: Int) {
        val memory = MemoryStorage.get(context).findAll().get(position)
        holder.itemView.tag = memory.id
        holder.name.text = memory.name
        holder.date.text = memory.date
    }

    override fun getItemCount(): Int {
        return MemoryStorage.get(context).size()
    }

}