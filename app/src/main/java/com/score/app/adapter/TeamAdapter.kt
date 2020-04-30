package com.score.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.score.app.R
import com.score.app.network.model.Team
import com.score.app.util.*
import kotlinx.android.synthetic.main.team_list_item.view.*

class TeamAdapter : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    private val data = mutableListOf<Team>()
    var teamClickListener: OnTeamClickListener? = null

    class TeamViewHolder(view: View, private var teamClickListener: OnTeamClickListener?) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val teamName: TextView = view.tv_team_name
        val teamWins: TextView = view.tv_team_wins
        val teamLosses: TextView = view.tv_team_losses

        init {
            view.setOnClickListener(this)
        }

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
        holder.teamWins.text = String.format(holder.itemView.context.getString(R.string.team_wins), data[position].wins)
        holder.teamLosses.text = String.format(holder.itemView.context.getString(R.string.team_losses), data[position].losses)
    }

    override fun getItemCount() = data.size

    fun getItemAtPosition(position: Int): Team = data[position]

    fun addData(teams: List<Team>) {
        data.clear()
        data.addAll(teams)
        notifyDataSetChanged()
    }

    fun sortAz() {
        addData(data.sortAz())
    }

    fun sortZa() {
        addData(data.sortZa())
    }

    fun sortByWins() {
        addData(data.sortByWins())
    }

    fun sortByWinsDescending() {
        addData(data.sortByWinsDescending())
    }

    fun sortByLosses() {
        addData(data.sortByLosses())
    }

    fun sortByLossesDescending() {
        addData(data.sortByLossesDescending())
    }

    interface OnTeamClickListener {
        fun onTeamClick(position: Int)
    }

}