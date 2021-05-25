package com.example.flowers

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowers.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var bindingClass: ActivityMainBinding
    lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        bindingClass.navView.setNavigationItemSelectedListener (this)

        var listItems = ArrayList<ListItem>()

        listItems.addAll(fillArray(resources.getStringArray(R.array.item_title_bouquets), resources.getStringArray(R.array.item_desc_bouquets), getImageId(R.array.item_image_bouquets)))

        var rcView = findViewById<RecyclerView>(R.id.recyclerView_main_content)
        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)

        adapter = RecyclerAdapter(listItems, this)
        rcView.adapter = adapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.id_bouquets -> {
                //Toast.makeText(this, "Букеты", Toast.LENGTH_SHORT).show()
                adapter.updateAdapter(fillArray(resources.getStringArray(R.array.item_title_bouquets), resources.getStringArray(R.array.item_desc_bouquets), getImageId(R.array.item_image_bouquets)))
            }
            R.id.id_flowers -> {
                //Toast.makeText(this, "Цветы", Toast.LENGTH_SHORT).show()
                adapter.updateAdapter(fillArray(resources.getStringArray(R.array.item_title_flowers), resources.getStringArray(R.array.item_desc_flowers), getImageId(R.array.item_image_flowers)))
            }
        }
        return true
    }

    fun fillArray(title:Array<String>, desc:Array<String>, img: IntArray):List<ListItem> {
        var listItemArray = ArrayList<ListItem>()
        for(a in 0..title.size - 1) {
            var listItem = ListItem(img[a], title[a], desc[a])
            listItemArray.add(listItem)
        }
        return listItemArray
    }

    fun getImageId(imageArrayId:Int):IntArray {
        var tArray:TypedArray = resources.obtainTypedArray(imageArrayId)
        val size = tArray.length()
        val imagesIds = IntArray(size)
        for(i in imagesIds.indices) {
            imagesIds[i] = tArray.getResourceId(i, 0)
        }
        tArray.recycle()
        return imagesIds
    }
}