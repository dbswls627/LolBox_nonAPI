package com.example.lolbox
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
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
        when (list[position].box) {
            true -> holder.box.text = "상자획득 완료"
            false -> holder.box.text = "상자획득 가능"
            else -> holder.box.text="보유 챔피언 설정"
        }
        holder.itemView.setOnLongClickListener() {
            if (list[position].box!=null) {
                list[position].box = !list[position].box!!
                notifyDataSetChanged()
            }

                true
        }
        holder.itemView.setOnClickListener {
            if (list[position].box==null){
                val intent = Intent(holder.itemView.context, EditActivity::class.java)
                startActivityForResult(holder.itemView.context as Activity,intent,1,null)
            }
        }
    }
}