package com.example.listedpoc.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.listedpoc.databinding.FragmentCoursesBinding
import com.example.listedpoc.databinding.FragmentLinkBinding

class CoursesFragment : BaseFragment() {

    private var binding: FragmentCoursesBinding? = null

    companion object{
        var TAG = CoursesFragment::class.java.simpleName

        fun newInstance(): CoursesFragment {
            return CoursesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}