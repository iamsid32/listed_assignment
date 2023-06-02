package com.example.listedpoc.screens.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listedpoc.adapter.RecentLinkAdapter
import com.example.listedpoc.adapter.TopLinkAdapter
import com.example.listedpoc.constants.BaseAPIConstants
import com.example.listedpoc.databinding.FragmentRecentLinksBinding
import com.example.listedpoc.model.LinkData
import com.example.listedpoc.model.LinkModel

class RecentLinksFragment:Fragment() {

    private var binding: FragmentRecentLinksBinding? = null
    private lateinit var recentLinkAdapter: RecentLinkAdapter
    private var linkData : LinkData?=null

    companion object{
        var TAG = RecentLinksFragment::class.java.simpleName

        fun newInstance(linkData: LinkData?): RecentLinksFragment {
            return RecentLinksFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(BaseAPIConstants.KEY_RECENT_LINK_LIST,linkData)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRecentLinksBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBundles()
        setUpRecyclerView()
    }

    private fun setUpBundles(){
        if (arguments?.containsKey(BaseAPIConstants.KEY_RECENT_LINK_LIST)==true){
            linkData = arguments?.getSerializable(BaseAPIConstants.KEY_RECENT_LINK_LIST) as LinkData?
        }
    }

    private fun setUpRecyclerView(){
        binding?.rvRecentLinks?.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        recentLinkAdapter = RecentLinkAdapter(linkData?.recentLinks, requireContext()) { clickedItem ->
            copyToClipboard(requireContext(),clickedItem?.webLink)
            Toast.makeText(requireContext(), "Copied", Toast.LENGTH_SHORT).show()
        }

        binding?.rvRecentLinks?.adapter = recentLinkAdapter
    }

    private fun copyToClipboard(context: Context, text: String?) {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Copied Text", text)
        clipboardManager.setPrimaryClip(clipData)
    }

}