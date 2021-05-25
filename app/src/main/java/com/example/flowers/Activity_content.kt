package com.example.flowers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flowers.databinding.ActivityContentBinding

class Activity_content : AppCompatActivity() {

    lateinit var bindingClass : ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityContentBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.activityContentTitle.text = intent.getStringExtra(Constance.ITEM_TITLE)
        bindingClass.activityContentDescription.text = intent.getStringExtra(Constance.ITEM_DESCRIPTION)
        bindingClass.activityContentImage.setImageResource(intent.getIntExtra(Constance.ITEM_IMAGE, R.drawable.bouquet_1))
    }
}