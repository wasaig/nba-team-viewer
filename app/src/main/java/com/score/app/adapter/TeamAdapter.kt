package com.score.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.score.app.R
import com.score.app.network.model.Team
import kotlinx.android.synthetic.main.team_list_item.view.*

class TeamAdapter : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    private val data = mutableListOf<Team>()
    var teamClickListener: OnTeamClickListener? = null

    class TeamViewHolder(view: View, var teamClickListener: OnTeamClickListener?) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val teamName: TextView = view.tv_team_name
        val teamWins: TextView = view.tv_team_wins
        val teamLosses: TextView = view.tv_team_losses

        override fun onClick(v: View?) {
            teamClickListener?.onTeamClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.team_list_item, parent, false)

        return TeamViewHolder(view, teamClickListener)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.teamName.text = data[position].fullName
        holder.teamWins.text = data[position].wins.toString()
        holder.teamLosses.text = data[position].losses.toString()
    }

    override fun getItemCount() = data.size

    fun getItemAtPosition(position: Int): Team = data[position]

    fun addData(data: ArrayList<Team>) {
        this.data.clear()
        this.data.addAll(data)
    }

    interface OnTeamClickListener {
        fun onTeamClick(position: Int)
    }

}