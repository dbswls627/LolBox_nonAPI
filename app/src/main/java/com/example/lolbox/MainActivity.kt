package com.example.lolbox

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lolbox.mainFragment.Companion.filteredList
import com.example.lolbox.mainFragment.Companion.list
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, mainFragment())
            .commit()
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
                filteredList.clear()
                for (i in 0 until list.size) {
                    if (list[i].name.length >= searchText.length) {
                        if (list[i].name.substring(0, searchText.length).contains(searchText)) {
                            filteredList.add(list[i])
                        }
                    }
                }
                val fragment : mainFragment = supportFragmentManager.findFragmentById(R.id.container) as mainFragment
                fragment.filter()
            }
        })

    }
}