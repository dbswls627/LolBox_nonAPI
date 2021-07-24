package com.example.lolbox
import  android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.dialog.*

class nDialog(context :Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog)
        //window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        np.minValue=0
        np.maxValue=3
        np.value=mainFragment.n!!
        ok.setOnClickListener {
            mainFragment.n=np.value
            this.dismiss()
        }
        cancel_button.setOnClickListener {
            this.dismiss()
        }
    }
}
