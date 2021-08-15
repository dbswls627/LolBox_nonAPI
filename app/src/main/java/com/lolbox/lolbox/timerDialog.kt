package com.lolbox.lolbox
import  android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.timerdialog.*
import java.util.*

class timerDialog(context :Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timerdialog)
        //window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        day.maxValue=7
        day.minValue=0
        hour.maxValue=23
        hour.minValue=0
        min.maxValue=59
        min.minValue=0
        mainFragment.today= Calendar.getInstance()
        var dday=(mainFragment.dday?.minus(mainFragment.today.time.time))
        var d = dday/(1000*60*60*24)//1000=초   1000*60=분  1000*60*60=시    일 1000*60*60*24
        var h = dday/(1000*60*60)
        var m = dday/(1000*60)
        var s = dday/(1000)
        day.value = d.toInt()
        hour.value = (h-d*24).toInt()
        min.value = (m-h*60).toInt()
        t_ok.setOnClickListener {
            mainFragment.dday = Calendar.getInstance().getTimeInMillis() + (1000 * 60 * 60 * 24*day.value)+(1000 * 60 * 60*hour.value)+(1000 * 60 *min.value)
            db.boxDao().upadte(Box(0, mainFragment.n!!, mainFragment.dday,mainFragment.checkB))
            this.dismiss()
        }
        t_cancel_button.setOnClickListener {
            this.dismiss()
        }
    }
}


