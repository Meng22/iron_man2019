package com.example.mrtstation

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.net.BindException


class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    private val itemList: ArrayList<Data.datas> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Bind(itemList[position])
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val tv_nickname = v.findViewById<TextView>(R.id.tv_nickname)
        val tv_subject = v.findViewById<TextView>(R.id.tv_subject)

        fun Bind(item: Data.datas){
            tv_nickname.setText(item.nickname)
            tv_subject.setText(item.subject)
        }
    }

    fun updateList(list: ArrayList<Data.datas>){
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}