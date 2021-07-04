package com.example.lolbox

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = arrayListOf(
            Cham(R.drawable.one,"가렌", box = false, save = false),
            Cham(R.drawable.one,"가렌", box = false, save = false),
            Cham(R.drawable.one,"가렌", box = false, save = false),
            Cham(R.drawable.one,"가렌", box = false, save = false),
            Cham(R.drawable.one,"가렌", box = false, save = false),
            Cham(R.drawable.one,"가렌", box = false, save = false),
            Cham(R.drawable.one,"가렌", box = false, save = false),
            Cham(R.drawable.one,"가렌", box = false, save = false),
            Cham(R.drawable.one,"가렌", box = false, save = false),
            Cham(R.drawable.one,"가렌", box = false, save = false),
            Cham(R.drawable.one,"가렌", box = false, save = false),
            Cham(R.drawable.one,"가렌", box = false, save = false),
            Cham(R.drawable.one,"가렌", box = false, save = false),
            Cham(R.drawable.one,"가렌", box = false, save = false),
            Cham(R.drawable.one,"가렌", box = false, save = false)
        )
        rv.layoutManager=LinearLayoutManager(this)
        rv.adapter=adapter(list)
    }
}
