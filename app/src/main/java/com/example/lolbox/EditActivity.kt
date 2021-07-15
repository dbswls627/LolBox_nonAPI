package com.example.lolbox

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var boollist = arrayListOf<Bool>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        rv2.layoutManager = LinearLayoutManager(this)
        rv2.adapter=adapter2(boollist,this)
        for (index in mainFragment.list.indices){
            boollist.add(Bool(mainFragment.list[index].save))
        }
        edit.setOnClickListener {
            for (index in boollist.indices){
                mainFragment.list[index].save=boollist[index].save
            }
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}