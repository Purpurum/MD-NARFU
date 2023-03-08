package com.example.PR_3_1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.PR_3_1.Model.UserModelClass
import com.example.pr_3_1.R

class UserAdapter(val context: Context, val items: ArrayList<UserModelClass>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_user_layout,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]
        if (item.sex == "male"){
            holder.image.setImageResource(R.drawable.male)
        }
        else if(item.sex == "female"){
            holder.image.setImageResource(R.drawable.female)
        }
        else{
            holder.image.setImageResource(R.drawable.unknown)
        }
        holder.tvName.text = item.name
        holder.tvPhoneNumber.text = item.phoneNumber
    }


    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each item to
        val tvName: TextView = view.findViewById(R.id.name)
        val tvPhoneNumber: TextView = view.findViewById(R.id.phoneNumber)
        val image: ImageView = view.findViewById(R.id.Image)
    }
}