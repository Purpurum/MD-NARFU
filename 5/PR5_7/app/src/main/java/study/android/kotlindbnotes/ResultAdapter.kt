package study.android.kotlindbnotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResultAdapter (private val results: List<ResultEntity>) :
    RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    override fun getItemCount() = results.size

    class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView? = null
        var result: TextView? = null

        init {
            name = itemView.findViewById(android.R.id.text1)
            result = itemView.findViewById(android.R.id.text2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            android.R.layout.simple_list_item_2, parent, false)
        return ResultViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.name?.text = results[position].name
        holder.result?.text = results[position].result.toString()
    }
}