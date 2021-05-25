package com.example.flowers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (listArray:ArrayList<ListItem>, context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var listArrayRec = listArray
    var contextRec = context



    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val image_id = view.findViewById<ImageView>(R.id.item_image)
        val item_title = view.findViewById<TextView>(R.id.item_content_title)
        val item_desc = view.findViewById<TextView>(R.id.item_content_description)


        fun bind(listItem:ListItem, context: Context) {
            image_id.setImageResource(listItem.image_id)
            item_title.text = listItem.item_title

            var item_desc_cut = listItem.item_desc.substring(0,100) + "..."
            item_desc.text = item_desc_cut
            itemView.setOnClickListener() {
                //Toast.makeText(context, "Pressed: ${item_desc.text}", Toast.LENGTH_SHORT).show()

                val intent = Intent(context, Activity_content::class.java).apply {
                    putExtra(Constance.ITEM_TITLE, item_title.text.toString())
                    putExtra(Constance.ITEM_DESCRIPTION, listItem.item_desc)
                    putExtra(Constance.ITEM_IMAGE, listItem.image_id)
                }
                context.startActivity(intent)
            }
        }

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextRec)
        return ViewHolder(inflater.inflate(R.layout.item_main_content, parent, false))
    }



    override fun getItemCount(): Int {
        return listArrayRec.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listItem = listArrayRec.get(position)
        holder.bind(listItem, contextRec)
    }

    fun updateAdapter(listArray : List<ListItem>) {
        listArrayRec.clear()
        listArrayRec.addAll(listArray)
        notifyDataSetChanged()
    }

}