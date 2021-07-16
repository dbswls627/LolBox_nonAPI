package com.example.lolbox
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.list_item.view.*

class CustuomViewHolder(v : View,context: Context) : RecyclerView.ViewHolder(v){
    val img =v.img
    val name: TextView =v.name
    val box: TextView =v.text
    val boximg: ImageView=v.box
    val db = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "database-name"
    ).allowMainThreadQueries().build()
}

class adapter(val list:ArrayList<User>, val context : Context):RecyclerView.Adapter<CustuomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustuomViewHolder {
       val cellForRow=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return CustuomViewHolder(cellForRow,context)
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: CustuomViewHolder, position: Int) {
        holder.img.setImageResource(list[position].img)
        holder.name.text = list[position].name
        when (list[position].box) {
            true -> {
                holder.box.text = "상자획득 완료"
                holder.boximg.setImageResource(R.drawable.boxot)
            }
            false -> {
                holder.box.text = "상자획득 가능"
                holder.boximg.setImageResource(R.drawable.boxp)
            }
            else -> holder.box.text="보유 챔피언 설정"
        }
        holder.itemView.setOnLongClickListener() {
            if (list[position].box!=null) {
                list[position].box = !list[position].box!!
                holder.db.userDao().upadte(User(list[position].img,list[position].name,list[position].box,list[position].save))
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