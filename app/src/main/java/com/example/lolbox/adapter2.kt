package com.example.lolbox
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.editlist_item.view.*
import kotlinx.android.synthetic.main.list_item.view.*


class CustuomViewHolder2(v : View) : RecyclerView.ViewHolder(v){
    val ct: CheckedTextView =v.checkedTextView
}

class adapter2(val list:ArrayList<Cham>,val context : Context):RecyclerView.Adapter<CustuomViewHolder2>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustuomViewHolder2 {
        val cellForRow=LayoutInflater.from(parent.context).inflate(R.layout.editlist_item,parent,false)
        return CustuomViewHolder2(cellForRow)
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: CustuomViewHolder2, position: Int) {
        holder.ct.text = list[position].name
        holder.itemView.setOnClickListener {
            holder.ct.isChecked = !holder.ct.isChecked
        }
    }

}