package com.example.lolbox
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class Cham(val img: Int, val name: String, var box: Boolean, var save: Boolean)
class CustuomViewHolder(v : View) : RecyclerView.ViewHolder(v){
    val img =v.img
    val name: TextView =v.name
}

class adapter(val list:ArrayList<Cham>):RecyclerView.Adapter<CustuomViewHolder>(){
    var ArrayList: ArrayList<Cham>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustuomViewHolder {
       val cellForRow=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return CustuomViewHolder(cellForRow)
    }
    fun filterList(filteredList: ArrayList<Cham>?) {
        ArrayList =filteredList
        notifyDataSetChanged()
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: CustuomViewHolder, position: Int) {
        holder.img.setImageResource(list[position].img)
        holder.name.setText(list[position].name)
  
        }
    }
}