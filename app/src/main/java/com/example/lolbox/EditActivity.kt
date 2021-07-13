package com.example.lolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.fragment_main.*

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        rv2.layoutManager = LinearLayoutManager(this)
        rv2.adapter=adapter2(mainFragment.list,this)
    }
}