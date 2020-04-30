package com.score.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.score.app.R
import com.score.app.network.model.Player
import kotlinx.android.synthetic.main.player_list_item.view.*

class PlayerAdapter : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    private val data = mutableListOf<Player>()

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerFistName: TextView = view.tv_player_first_name
        val playerLastName: TextView = view.tv_player_last_name
        val playerPosition: TextView = view.tv_player_position
        val playerNumber: TextView = view.tv_player_number
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.player_list_item, parent, false)

        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.playerFistName.text = data[position].firstName
        holder.playerLastName.text = data[position].lastName
        holder.playerPosition.text = String.format(holder.itemView.context.getString(R.string.player_position), data[position].position)
        holder.playerNumber.text = String.format(holder.itemView.context.getString(R.string.player_number), data[position].number)
    }

    override fun getItemCount() = data.size

    fun addData(data: ArrayList<Player>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}