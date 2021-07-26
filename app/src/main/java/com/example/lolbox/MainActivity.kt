package com.example.lolbox

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    var time :String?=null
    var day :Long = 0
   // @RequiresApi(Build.VERSION_CODES.N)
    //@SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this, getString(R.string.admob_app_id))
        adView.loadAd(AdRequest.Builder().build())
        var checkList: Int =0

        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        if ( db.userDao().getAll().toString()=="[]") {
            for (i in mainFragment.img.indices) {
                db.userDao().insert(User(mainFragment.img[i], mainFragment.name[i], false, false))
            }
            db.boxDao().insert(Box(0,3, null,false))
        }
       mainFragment.dday = db.boxDao().getTime()
       mainFragment.checkB = db.boxDao().getB()
       if (!mainFragment.checkB){
           var dlg =helpDialog(this)
           dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
           val lp = WindowManager.LayoutParams()
           lp.copyFrom(dlg.window?.attributes)
           lp.width = WindowManager.LayoutParams.MATCH_PARENT
           lp.height = WindowManager.LayoutParams.WRAP_CONTENT
           dlg.show()
           dlg.window?.attributes = lp
           dlg.show()

       }
        mainFragment.n=db.boxDao().getN()
        mainFragment.list=db.userDao().getAll() as ArrayList<User>

        showtimer()
        roof()

        for (index in mainFragment.list.indices ){
            if(mainFragment.list[index].save){
                mainFragment.savelist.add(mainFragment.list[index])
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, mainFragment()).commit()
        all.setOnClickListener {
            checkList=0

            val fragment : mainFragment = supportFragmentManager.findFragmentById(R.id.container) as mainFragment
            fragment.all()
        }
        save.setOnClickListener {
            checkList=1
            val fragment: mainFragment = supportFragmentManager.findFragmentById(R.id.container) as mainFragment
            mainFragment.savelist.clear()
            mainFragment.savelist.add(User(R.drawable.ic_setting,"",null,true))
            for (index in mainFragment.list.indices) {
                if (mainFragment.list[index].save) {
                    mainFragment.savelist.add(mainFragment.list[index])
                }
            }
           fragment.save()
        }
        boxIcon.setOnClickListener {
            val dlg = nDialog(this)
            dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dlg.window?.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            dlg.show()
            dlg.window?.attributes = lp

        }
        n.setOnClickListener {
            val dlg = nDialog(this)
            dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dlg.window?.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            dlg.show()
            dlg.window?.attributes = lp
            dlg.show()

        }
        timer.setOnClickListener {
            val dlg = timerDialog(this)
            dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dlg.window?.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            dlg.show()
            dlg.window?.attributes = lp
            dlg.show()
        }
        timerIcon.setOnClickListener {
            val dlg = timerDialog(this)
            dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dlg.window?.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            dlg.show()
            dlg.window?.attributes = lp
            dlg.show()
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
                if (checkList == 0) {
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
                if (checkList == 1) {
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
                if (mainFragment.list[index].save) {
                    mainFragment.savelist.add(mainFragment.list[index])

                }
            }
            fragment.save()
        }
       else return
    }
    private val mDelayHandler: Handler by lazy {
        Handler()
    }
    private fun roof(){
        mDelayHandler.postDelayed(::showtimer, 1000) // 10초 후에 showGuest 함수를 실행한다.
    }

    private fun showtimer(){
        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        if (mainFragment.dday!=null){
            if (-(1000 * 60 * 60 * 24 * 7)<day&&day<0){
                mainFragment.n= mainFragment.n!! +1
                mainFragment.dday=mainFragment.dday+(1000 * 60 * 60 * 24 * 7)
                db.boxDao().upadte(Box(0, mainFragment.n!!,mainFragment.dday,mainFragment.checkB))
            }
            else if (-(1000 * 60 * 60 * 24 * 7)*2<day&&day<-(1000 * 60 * 60 * 24 * 7)){
                mainFragment.n= mainFragment.n!! +2
                if (mainFragment.n!! >3) mainFragment.n=3
                mainFragment.dday=mainFragment.dday+(1000 * 60 * 60 * 24 * 7)*2
                db.boxDao().upadte(Box(0, mainFragment.n!!,mainFragment.dday,mainFragment.checkB))
            }
        }
        mainFragment.today= Calendar.getInstance()
        day=(mainFragment.dday.minus(mainFragment.today.time.time))
        n.text=mainFragment.n.toString()+"개 획득 가능"
        var d = day/(1000*60*60*24)//1000=초   1000*60=분  1000*60*60=시    일 1000*60*60*24
        var h = day/(1000*60*60)
        var m = day/(1000*60)
        var s = day/(1000)
        time=d.toString()+"일"+(h-d*24).toString()+"시"+(m-h*60).toString()+"분"+(s-m*60).toString()+"초"

        if (mainFragment.n==3) timer.text="꽉참"
        else if (mainFragment.dday.toInt()==0){
            timer.text="타이머설정"
        }
        else timer.text=time



        roof() // 코드 실행뒤에 계속해서 반복하도록 작업한다.
    }

}

