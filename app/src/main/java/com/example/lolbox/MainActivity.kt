package com.example.lolbox

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        if ( db.userDao().getAll().toString()=="[]") {
            for (i in mainFragment.img.indices) {
                db.userDao().insert(User(mainFragment.img[i], mainFragment.name[i], false, false))
            }
        }
        Log.d("test",db.userDao().getAll().toString())
        for (index in mainFragment.img.indices ) {
            mainFragment.list.add(Cham(mainFragment.img[index],mainFragment.name[index],false,false))
        }

        for (index in mainFragment.img.indices ){
            if(mainFragment.list[index].save){
                mainFragment.savelist.add(mainFragment.list[index])
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, mainFragment()).commit()
        save.setOnClickListener {
            val fragment: mainFragment = supportFragmentManager.findFragmentById(R.id.container) as mainFragment
            mainFragment.savelist.clear()
            mainFragment.savelist.add(Cham(R.drawable.ic_setting,"",null,true))
            for (index in mainFragment.list.indices) {
                if (mainFragment.list[index].save!!) {
                    mainFragment.savelist.add(mainFragment.list[index])
                }
            }
           fragment.save()
        }
            all.setOnClickListener {
                val fragment : mainFragment = supportFragmentManager.findFragmentById(R.id.container) as mainFragment
                fragment.all()
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
                for (i in 0 until mainFragment.list.size) {
                    if (mainFragment.list[i].name.length >= searchText.length) {
                        if (mainFragment.list[i].name.substring(0, searchText.length).contains(searchText)) {
                            mainFragment.searchlist.add(mainFragment.list[i])
                        }
                    }
                }
                val fragment : mainFragment = supportFragmentManager.findFragmentById(R.id.container) as mainFragment
                fragment.search()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK) {
            val fragment: mainFragment = supportFragmentManager.findFragmentById(R.id.container) as mainFragment
            mainFragment.savelist.clear()
            mainFragment.savelist.add(Cham(R.drawable.ic_setting,"",null,true))
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