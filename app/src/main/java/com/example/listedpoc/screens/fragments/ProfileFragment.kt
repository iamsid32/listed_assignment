package com.example.listedpoc.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.listedpoc.databinding.FragmentCampaignsBinding
import com.example.listedpoc.databinding.FragmentProfileBinding

class ProfileFragment:BaseFragment() {

    private var binding: FragmentProfileBinding? = null

    companion object{
        var TAG = ProfileFragment::class.java.simpleName

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}