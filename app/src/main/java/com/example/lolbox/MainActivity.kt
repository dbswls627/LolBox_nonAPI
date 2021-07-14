package com.example.lolbox

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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