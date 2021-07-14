package com.example.lolbox

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        rv2.layoutManager = LinearLayoutManager(this)
        rv2.adapter=adapter2(mainFragment.list,this)
        mainFragment.clonelist=mainFragment.list
        edit.setOnClickListener {
           // mainFragment.list=mainFragment.clonelist
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}