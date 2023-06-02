package com.example.listedpoc.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.listedpoc.databinding.FragmentCampaignsBinding
import com.example.listedpoc.databinding.FragmentCoursesBinding

class CampaignsFragment:BaseFragment() {

    private var binding: FragmentCampaignsBinding? = null

    companion object{
        var TAG = CampaignsFragment::class.java.simpleName

        fun newInstance(): CampaignsFragment {
            return CampaignsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCampaignsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}