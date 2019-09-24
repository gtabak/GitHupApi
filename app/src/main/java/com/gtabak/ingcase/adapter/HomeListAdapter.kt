package com.gtabak.ingcase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gtabak.ingcase.databinding.ListItemLayoutBinding
import com.gtabak.ingcase.model.RepoModel
import com.gtabak.ingcase.room_database.AppDatabase

class HomeListAdapter(
    private val items: List<RepoModel>,
    var db: AppDatabase?,
    val listener: (Int) -> Unit
) : RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemLayoutBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], position, listener)


    inner class ViewHolder(val binding: ListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RepoModel, pos: Int, listener: (Int) -> Unit) = with(itemView) {
            with(binding) {

                binding.repoName = item.name

                if (db!!.repoDao().getRow(item.id) != null) {
                    binding.starImage.visibility = View.VISIBLE
                } else {
                    binding.starImage.visibility = View.GONE
                }
                binding.line.setOnClickListener {
                    listener(pos)
                }
                binding.executePendingBindings()
            }
        }
    }
}