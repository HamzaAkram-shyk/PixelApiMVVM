package com.example.myapplication.presenter.ui.gridview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.GridItemBinding
import com.example.myapplication.domain.model.PixelResponse


class GridViewAdapter(
    private val items: ArrayList<PixelResponse.Hit> = arrayListOf(),
    private val onClick: (PixelResponse.Hit) -> Unit
) : RecyclerView.Adapter<GridViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GridItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClick(item)
        }
        holder.binding.executePendingBindings()
    }

    fun addItems(list: List<PixelResponse.Hit>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(val binding: GridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PixelResponse.Hit) {
            binding.item = item
        }
    }


}