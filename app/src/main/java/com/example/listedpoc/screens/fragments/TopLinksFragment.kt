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
import com.example.listedpoc.adapter.TopLinkAdapter
import com.example.listedpoc.constants.BaseAPIConstants
import com.example.listedpoc.databinding.FragmentTopLinksBinding
import com.example.listedpoc.model.LinkData
import com.example.listedpoc.model.LinkModel
import com.example.listedpoc.model.TopLink

class TopLinksFragment:Fragment() {

    private var binding: FragmentTopLinksBinding? = null
    private lateinit var topLinkAdapter: TopLinkAdapter
    private var linkData : LinkData?=null

    companion object{
        var TAG = TopLinksFragment::class.java.simpleName

        fun newInstance(linkData: LinkData?): TopLinksFragment {
            return TopLinksFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(BaseAPIConstants.KEY_TOP_LINK_LIST,linkData)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTopLinksBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBundles()
        setUpRecyclerView()
    }

    private fun setUpBundles(){
        if (arguments?.containsKey(BaseAPIConstants.KEY_TOP_LINK_LIST)==true){
            linkData = arguments?.getSerializable(BaseAPIConstants.KEY_TOP_LINK_LIST) as LinkData?
        }
    }

    private fun setUpRecyclerView(){
        binding?.rvTopLinks?.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        topLinkAdapter = TopLinkAdapter(linkData?.topLinks, requireContext()) { clickedItem ->
            copyToClipboard(requireContext(),clickedItem?.webLink)
            Toast.makeText(requireContext(), "Copied", Toast.LENGTH_SHORT).show()
        }

        binding?.rvTopLinks?.adapter = topLinkAdapter
    }

    private fun copyToClipboard(context: Context, text: String?) {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Copied Text", text)
        clipboardManager.setPrimaryClip(clipData)
    }
}