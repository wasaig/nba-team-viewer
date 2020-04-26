package com.score.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.score.app.ui.nbateam.NBATeamFragment

class NBATeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.n_b_a_team_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, NBATeamFragment.newInstance())
                    .commitNow()
        }
    }
}
