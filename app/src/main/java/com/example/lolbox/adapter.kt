package com.example.lolbox
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class Cham(val img: Int, val name: String, var box: Boolean?, var save: Boolean)
class CustuomViewHolder(v : View) : RecyclerView.ViewHolder(v){
    val img =v.img
    val name: TextView =v.name
    val box: TextView =v.text
}

class adapter(val list:ArrayList<Cham>,val context : Context):RecyclerView.Adapter<CustuomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustuomViewHolder {
       val cellForRow=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return CustuomViewHolder(cellForRow)
    }
    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: CustuomViewHolder, position: Int) {
        holder.img.setImageResource(list[position].img)
        holder.name.text = list[position].name
        if (list[position].box==true) holder.box.text = "상자획득 완료"
        if (list[position].box==false) holder.box.text = "상자획득 가능"
        else holder.box.text="보유 챔피언 설정"
        holder.itemView.setOnLongClickListener() {
            list[position].box = !list[position].box!!
            notifyDataSetChanged()
            true
        }
    }
}