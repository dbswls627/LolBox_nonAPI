package com.example.lolbox

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var check: Int =0
        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        if ( db.userDao().getAll().toString()=="[]") {
            for (i in mainFragment.img.indices) {
                db.userDao().insert(User(mainFragment.img[i], mainFragment.name[i], false, false))
            }
        }
        Log.d("test1",db.userDao().getAllName().toString())
        mainFragment.list=db.userDao().getAll() as ArrayList<User>

        Log.d("test2", mainFragment.list.toString())


        for (index in mainFragment.img.indices ){
            if(mainFragment.list[index].save){
                mainFragment.savelist.add(mainFragment.list[index])
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, mainFragment()).commit()
        all.setOnClickListener {
            check=0

            val fragment : mainFragment = supportFragmentManager.findFragmentById(R.id.container) as mainFragment
            fragment.all()
        }
        save.setOnClickListener {
            check=1

            val fragment: mainFragment = supportFragmentManager.findFragmentById(R.id.container) as mainFragment
            mainFragment.savelist.clear()
            mainFragment.savelist.add(User(R.drawable.ic_setting,"",null,true))
            for (index in mainFragment.list.indices) {
                if (mainFragment.list[index].save!!) {
                    mainFragment.savelist.add(mainFragment.list[index])
                }
            }
           fragment.save()
        }



        search.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val searchText: String = search.text.toString()
                searchFilter(searchText)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
            private fun searchFilter(searchText: String) {
                mainFragment.searchlist.clear()
                if (check == 0) {
                    for (i in 0 until mainFragment.list.size) {
                        if (mainFragment.list[i].name.length >= searchText.length) {
                            if (mainFragment.list[i].name.substring(0, searchText.length).contains(searchText)) {
                                mainFragment.searchlist.add(mainFragment.list[i])
                            }
                        }
                    }
                    val fragment: mainFragment = supportFragmentManager.findFragmentById(R.id.container) as mainFragment
                    fragment.search()
                }
                if (check == 1) {
                    for (i in 0 until mainFragment.savelist.size) {
                        if (mainFragment.savelist[i].name.length >= searchText.length) {
                            if (mainFragment.savelist[i].name.substring(0, searchText.length).contains(searchText)) {
                                mainFragment.searchlist.add(mainFragment.savelist[i])
                            }
                        }
                    }
                    val fragment: mainFragment = supportFragmentManager.findFragmentById(R.id.container) as mainFragment
                    fragment.search()
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK) {
            val fragment: mainFragment = supportFragmentManager.findFragmentById(R.id.container) as mainFragment
            mainFragment.savelist.clear()
            mainFragment.savelist.add(User(R.drawable.ic_setting,"",null,true))
            for (index in mainFragment.list.indices) {
                if (mainFragment.list[index].save!!) {
                    mainFragment.savelist.add(mainFragment.list[index])

                }
            }
            fragment.save()
        }
       else return
    }
}