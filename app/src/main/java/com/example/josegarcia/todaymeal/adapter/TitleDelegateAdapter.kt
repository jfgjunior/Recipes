package com.example.josegarcia.todaymeal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.josegarcia.todaymeal.R
import kotlinx.android.synthetic.main.item_title.view.*

class TitleDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(viewGroup: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(getLayoutId(), viewGroup, false)
        return TitleViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, model: Any) {
        (viewHolder as TitleViewHolder).bind(model as Int)
    }

    override fun getLayoutId() = R.layout.item_title

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(textId: Int) {
            itemView.title.text = itemView.context.resources.getString(textId)
        }
    }
}