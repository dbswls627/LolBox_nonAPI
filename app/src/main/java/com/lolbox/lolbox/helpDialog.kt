package com.lolbox.lolbox
import  android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.helpdialog.*

class helpDialog(context :Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.helpdialog)
        button1.setOnClickListener {
            db.boxDao().upadte(Box(0, mainFragment.n!!, mainFragment.dday,true))
            this.dismiss()
        }
        button2.setOnClickListener {
            this.dismiss()
        }
    }
}
