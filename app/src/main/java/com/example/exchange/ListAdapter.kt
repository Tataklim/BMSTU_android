package com.example.exchange

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val clickListener: (String) -> Unit) : RecyclerView.Adapter<ListAdapter.ElemViewHolder> () {
    var data = listOf<ExampleData> ()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElemViewHolder {
        return ElemViewHolder.from(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ElemViewHolder, position: Int) {
        val item = data[position]
        holder.setDataAndListener(item.date, item.cryptoCost, item.currency, clickListener)
    }

    class ElemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)  {
        private val dateViewRow: TextView = itemView.findViewById(R.id.dateId)
        private val textViewRow: TextView = itemView.findViewById(R.id.textId)
        private val currencyViewRow : TextView = itemView.findViewById(R.id.currencyId)

        fun setDataAndListener(item : String, costId : String, currencyItem: String, clickListener: (String) -> Unit) {
            dateViewRow.text = item
            textViewRow.text = costId
            currencyViewRow.text=currencyItem
            itemView.setOnClickListener{clickListener(item)}
        }

        companion object {
            fun from(parent: ViewGroup) : ElemViewHolder {
                val context = parent.context
                val layoutIdForListItem = R.layout.list_item_1
                val inflater = LayoutInflater.from(context)
                val shouldAttachToParentImmediately = false
                val view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately)
                return ElemViewHolder(view)
            }
        }
    }
}