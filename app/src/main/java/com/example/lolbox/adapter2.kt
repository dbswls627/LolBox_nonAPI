package com.example.lolbox
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.editlist_item.view.*
import kotlinx.android.synthetic.main.list_item.view.*


class CustuomViewHolder2(v : View) : RecyclerView.ViewHolder(v){
    val ct: CheckedTextView =v.checkedTextView
}
class Bool(var save: Boolean)
class adapter2(val boollist:ArrayList<Bool>,val context : Context):RecyclerView.Adapter<CustuomViewHolder2>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustuomViewHolder2 {
        val cellForRow=LayoutInflater.from(parent.context).inflate(R.layout.editlist_item,parent,false)
        return CustuomViewHolder2(cellForRow)
    }

    override fun getItemCount()=boollist.size

    override fun onBindViewHolder(holder: CustuomViewHolder2, position: Int) {
        holder.ct.text = mainFragment.list[position].name
        holder.ct.isChecked=mainFragment.list[position].save
        holder.itemView.setOnClickListener {
            holder.ct.isChecked = !holder.ct.isChecked
            boollist[position].save=true
        }

    }
}