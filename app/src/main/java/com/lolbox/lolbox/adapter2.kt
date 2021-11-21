package com.lolbox.lolbox
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.editlist_item.view.*


class CustuomViewHolder2(v : View) : RecyclerView.ViewHolder(v){
    val ct: CheckedTextView =v.checkedTextView
}
class Bool(var save: Boolean)
class adapter2(val boollist:ArrayList<Bool>):RecyclerView.Adapter<CustuomViewHolder2>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustuomViewHolder2 {
        val cellForRow=LayoutInflater.from(parent.context).inflate(R.layout.editlist_item,parent,false)
        return CustuomViewHolder2(cellForRow)
    }

    override fun getItemCount()=boollist.size

    override fun onBindViewHolder(holder: CustuomViewHolder2, position: Int) {
        holder.ct.text = mainFragment.list[position].name
        holder.ct.isChecked=mainFragment.list[position].save
        if (mainFragment.bool==true){
            holder.ct.isChecked=true
        }
        if (mainFragment.bool==false){
            holder.ct.isChecked=false
        }
        holder.itemView.setOnClickListener {
            holder.ct.isChecked = !holder.ct.isChecked
            boollist[position].save=holder.ct.isChecked

        }

    }
}