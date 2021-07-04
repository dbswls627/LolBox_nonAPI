package com.example.lolbox
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class Cham(val img: Int, val name: String, val box: Boolean, val save: Boolean)
class CustuomViewHolder(v : View) : RecyclerView.ViewHolder(v){
    val img =v.img
    val name: TextView =v.name
}

class adapter(val list:ArrayList<Cham>):RecyclerView.Adapter<CustuomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustuomViewHolder {
       val cellForRow=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return CustuomViewHolder(cellForRow)
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: CustuomViewHolder, position: Int) {
        holder.img.setImageResource(list[position].img)
        holder.name.setText(list[position].name)
    }
}