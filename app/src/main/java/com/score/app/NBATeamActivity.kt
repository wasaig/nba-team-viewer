package com.score.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.score.app.ui.TeamListFragment

class NBATeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.n_b_a_team_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TeamListFragment.newInstance())
                    .commitNow()
        }
    }
}
