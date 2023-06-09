package com.example.localdbroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.localdbroom.model.Todo

class CustomAdapter(private val dataSource: ArrayList<Todo>) :
    RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    private var mOnClickListener: OnClickListener? = null

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.titleTV)
        val desc = itemView.findViewById<TextView>(R.id.descTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.sample_layout, parent, false)
        return CustomViewHolder(item)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.title.text = dataSource[position].title
        holder.desc.text = dataSource[position].description
        holder.itemView.setOnClickListener {
            if (mOnClickListener != null) {
                mOnClickListener!!.onClick(position, dataSource[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.mOnClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, todo: Todo)
    }
}