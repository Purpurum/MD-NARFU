package com.example.lr_3_6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import com.example.lr_3_6.databinding.ListItemBinding

class ColorItem(val title: String, val color: Int)
class ColorsAdapter(var colors: List<ColorItem>, var onitemClick: (coloItem: ColorItem) -> Unit) :
    RecyclerView.Adapter<ColorsAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(colorItem: ColorItem) {

            binding.item.apply {
                setBackgroundResource(colorItem.color)
                text = colorItem.title
            }
            binding.root.setOnClickListener {

                onitemClick(colorItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount(): Int = colors.size


}