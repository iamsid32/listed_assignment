package com.example.listedpoc

import android.os.Bundle
import com.example.listedpoc.databinding.ActivityMainBinding
import com.example.listedpoc.screens.activity.BaseActivity
import com.example.listedpoc.screens.fragments.CampaignsFragment
import com.example.listedpoc.screens.fragments.CoursesFragment
import com.example.listedpoc.screens.fragments.LinksFragment
import com.example.listedpoc.screens.fragments.ProfileFragment


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.flFullScreen, LinksFragment.newInstance()).commit()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.links -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flFullScreen, LinksFragment.newInstance()).commit()
                    true
                }
                R.id.courses -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flFullScreen, CoursesFragment.newInstance()).commit()
                    true
                }
                R.id.campaigns -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flFullScreen, CampaignsFragment.newInstance()).commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flFullScreen, ProfileFragment.newInstance()).commit()
                    true
                }
                else -> false
            }
        }
    }

}