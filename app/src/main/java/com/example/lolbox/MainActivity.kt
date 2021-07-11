package com.example.lolbox

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.lolbox.mainFragment.Companion.searchlist
import com.example.lolbox.mainFragment.Companion.list
import com.example.lolbox.mainFragment.Companion.savelist
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, mainFragment())
            .commit()
        save.setOnClickListener {
            for (i in 0 until list.size) {
                if (list[i].save!!) {
                    savelist.add(list[i])
                }
            }
            val fragment : mainFragment = supportFragmentManager.findFragmentById(R.id.container) as mainFragment
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
                searchlist.clear()
                for (i in 0 until list.size) {
                    if (list[i].name.length >= searchText.length) {
                        if (list[i].name.substring(0, searchText.length).contains(searchText)) {
                            searchlist.add(list[i])
                        }
                    }
                }
                val fragment : mainFragment = supportFragmentManager.findFragmentById(R.id.container) as mainFragment
                fragment.search()
            }
        })

    }
}