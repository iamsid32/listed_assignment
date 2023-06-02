package com.example.listedpoc.screens.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listedpoc.R
import com.example.listedpoc.adapter.TrendingDataAdapter
import com.example.listedpoc.databinding.FragmentLinkBinding
import com.example.listedpoc.model.DashboardModel
import com.example.listedpoc.model.TrendingData
import com.example.listedpoc.screens.activity.BaseActivity
import com.example.listedpoc.view_model.DashboardViewModel
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.gson.Gson
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class LinksFragment:BaseFragment() {

    private var binding: FragmentLinkBinding? = null
    private lateinit var trendingDataAdapter: TrendingDataAdapter
    private lateinit var dashboardViewModel: DashboardViewModel
    private var dashboardModel:DashboardModel?=null

    companion object{
        var TAG = LinksFragment::class.java.simpleName

        fun newInstance(): LinksFragment {
            return LinksFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLinkBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModels()
        setObserver()
        setListeners()
        setUpTrendingRecyclerView()
        binding?.tvGreet?.text = greetUserByTime()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.flTabLayoutFragment, TopLinksFragment.newInstance(dashboardModel?.data)).commit()
        dashboardViewModel.getDashboardData(true)
    }

    private fun setListeners(){
        binding?.apply {
            tvTopLinks.setOnClickListener(onClickListener)
            tvRecentLinks.setOnClickListener(onClickListener)
            llViewAllLinks.setOnClickListener(onClickListener)
            llViewAnalytics.setOnClickListener(onClickListener)
            llChatWithUs.setOnClickListener(onClickListener)
            llAskQuestion.setOnClickListener(onClickListener)
            ivSettings.setOnClickListener(onClickListener)
        }
    }

    private val onClickListener : View.OnClickListener = View.OnClickListener { view ->
        when(view?.id){
            R.id.tvTopLinks -> {
                binding?.tvTopLinks?.background = requireActivity().getDrawable(R.drawable.bg_selected_tab)
                binding?.tvTopLinks?.setTextColor(Color.parseColor("#ffffff"))
                binding?.tvRecentLinks?.background = requireActivity().getDrawable(R.drawable.bg_unselected_tab)
                binding?.tvRecentLinks?.setTextColor(Color.parseColor("#999CA0"))

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.flTabLayoutFragment, TopLinksFragment.newInstance(dashboardModel?.data)).commit()
            }

            R.id.tvRecentLinks -> {
                binding?.tvTopLinks?.background = requireActivity().getDrawable(R.drawable.bg_unselected_tab)
                binding?.tvTopLinks?.setTextColor(Color.parseColor("#999CA0"))
                binding?.tvRecentLinks?.background = requireActivity().getDrawable(R.drawable.bg_selected_tab)
                binding?.tvRecentLinks?.setTextColor(Color.parseColor("#ffffff"))

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.flTabLayoutFragment, RecentLinksFragment.newInstance(dashboardModel?.data)).commit()
            }

            R.id.llViewAllLinks -> {}
            R.id.llViewAnalytics -> {}
            R.id.llChatWithUs -> openWhatsApp("7071696034")
            R.id.llAskQuestion -> {}
            R.id.ivSettings -> {}
        }
    }

    private fun setUpChart(entries:MutableList<Entry>){
        val dataSet = LineDataSet(entries, "Clicks")
        dataSet.color = Color.BLUE
        dataSet.setCircleColor(Color.BLUE)
        dataSet.lineWidth = 2f
        dataSet.circleRadius = 4f
        dataSet.setDrawCircleHole(false)
        dataSet.valueTextSize = 12f
        dataSet.setLineWidth(2.5f)
        dataSet.setColor(Color.parseColor("#0E6FFF"))
        dataSet.setCircleColor(R.color.white);
        dataSet.setHighLightColor(Color.RED);
        dataSet.setDrawValues(false)
        dataSet.setCircleRadius(10f)
        dataSet.setCubicIntensity(0.2f)
        dataSet.setDrawFilled(true)
        dataSet.setFillColor(Color.BLACK)
        dataSet.setFillAlpha(80)
        val drawable = requireActivity().getDrawable(R.drawable.bg_gradient)
        drawable?.alpha = 90
        dataSet.setFillDrawable(drawable)
        dataSet.setDrawCircles(false)
        val lineData = LineData(dataSet)
        binding?.chart?.data = lineData
        binding?.chart?.description?.isEnabled = false
        binding?.chart?.setTouchEnabled(true)
        binding?.chart?.isDragEnabled = true
        binding?.chart?.setScaleEnabled(true)
        binding?.chart?.setPinchZoom(true)

        val xAxis = binding?.chart?.xAxis
        xAxis?.position = XAxis.XAxisPosition.BOTTOM
        xAxis?.setDrawGridLines(true)
        xAxis?.granularity = 1f
        xAxis?.valueFormatter = MonthValueFormatter()

        val yAxis = binding?.chart?.axisLeft
        yAxis?.setDrawGridLines(true)
        yAxis?.axisMinimum = 0f
        yAxis?.axisMaximum = 100f

        binding?.chart?.axisRight?.isEnabled = false

        binding?.chart?.invalidate()
    }

    private inner class MonthValueFormatter : ValueFormatter() {
        private val months = arrayOf(
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        )

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val monthIndex = value.toInt() % months.size
            return months[monthIndex]
        }
    }

    private fun setUpTrendingRecyclerView(){
        binding?.rvTrendingData?.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        val trendingList = mutableListOf<TrendingData>()
        trendingList.add(TrendingData(icon = R.drawable.ic_clicks, title = dashboardModel?.todayClicks?.toString() ?: "0", "Today's Clicks"))
        trendingList.add(TrendingData(icon = R.drawable.ic_location, title = dashboardModel?.topLocation ?: "Not Available", "Top Location"))
        trendingList.add(TrendingData(icon = R.drawable.ic_web, title = dashboardModel?.topSource ?: "Not Available", "Top Source"))
        trendingDataAdapter = TrendingDataAdapter(trendingList, requireContext()) { clickedItem -> }

        binding?.rvTrendingData?.adapter = trendingDataAdapter
    }

    private fun initViewModels() {
        dashboardViewModel = getViewModel(fragment = this, viewModel = DashboardViewModel(activity as BaseActivity), className = DashboardViewModel::class.java)
    }

    private fun setObserver(){
        dashboardViewModel.dashBoardResponseLivedata.observe(requireActivity(), Observer {
            dashboardModel = it
            binding?.tvTopLinks?.background = requireActivity().getDrawable(R.drawable.bg_selected_tab)
            binding?.tvTopLinks?.setTextColor(Color.parseColor("#ffffff"))
            binding?.tvRecentLinks?.background = requireActivity().getDrawable(R.drawable.bg_unselected_tab)
            binding?.tvRecentLinks?.setTextColor(Color.parseColor("#999CA0"))

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flTabLayoutFragment, TopLinksFragment.newInstance(dashboardModel?.data)).commit()

            prepareDataForChart(it)

            binding?.tvDuration?.text = "${formatDateUptoMonth(JSONObject(it.data?.overallUrlChart.toString()).keys().next())} " +
                    "- ${formatDateUptoMonth(JSONObject(it.data?.overallUrlChart.toString()).keys().asSequence().last())}"

        })
    }

    private fun openWhatsApp(number: String) {
        number.replace("+", "").replace(" ", "")
        val sendIntent = Intent("android.intent.action.MAIN")
        sendIntent.putExtra("jid", "$number@s.whatsapp.net")
        sendIntent.putExtra(Intent.EXTRA_TEXT, "jhfvcsdhc")
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.setPackage("com.whatsapp")
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }

    fun greetUserByTime(): String {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)

        return when (hourOfDay) {
            in 0..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            in 16..23 -> "Good Evening"
            else -> "Hello"
        }
    }

    fun formatDateUptoMonth(dateString: String?): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM", Locale.getDefault())

        if (dateString == null){
            return ""
        }else{
            val date = inputFormat.parse(dateString)
            return outputFormat.format(date)
        }

    }

    private fun prepareDataForChart(dashboardModel: DashboardModel){
        val gson = Gson()
        val json = gson.toJson(dashboardModel.data?.overallUrlChart)

        var jan = 0
        var feb = 0
        var march = 0
        var april = 0
        var may = 0
        var june = 0
        var july = 0
        var aug = 0
        var sept = 0
        var oct = 0
        var nov = 0
        var dec = 0


        JSONObject(dashboardModel.data?.overallUrlChart.toString()).keys().forEach {
            if (it.substring(5,7).toInt() == 1){
                jan = jan + JSONObject(json).optInt(it)
            }
            if (it.substring(5,7).toInt() == 2){
                feb = feb + JSONObject(json).optInt(it)
            }
            if (it.substring(5,7).toInt() == 3){
                march = march + JSONObject(json).optInt(it)
            }
            if (it.substring(5,7).toInt() == 4){
                april = april + JSONObject(json).optInt(it)
            }
            if (it.substring(5,7).toInt() == 5){
                may = may + JSONObject(json).optInt(it)
            }
            if (it.substring(5,7).toInt() == 6){
                june = june + JSONObject(json).optInt(it)
            }
            if (it.substring(5,7).toInt() == 7){
                july = july + JSONObject(json).optInt(it)
            }
            if (it.substring(5,7).toInt() == 8){
                aug = aug + JSONObject(json).optInt(it)
            }
            if (it.substring(5,7).toInt() == 9){
                sept = sept + JSONObject(json).optInt(it)
            }
            if (it.substring(5,7).toInt() == 10){
                oct = oct + JSONObject(json).optInt(it)
            }
            if (it.substring(5,7).toInt() == 11){
                nov = nov + JSONObject(json).optInt(it)
            }
            if (it.substring(5,7).toInt() == 12){
                dec = dec + JSONObject(json).optInt(it)
            }
        }

        val entries = mutableListOf(
            Entry(0f, jan.toFloat()),
            Entry(1f, feb.toFloat()),
            Entry(2f, march.toFloat()),
            Entry(3f, april.toFloat()),
            Entry(4f, may.toFloat()),
            Entry(5f, june.toFloat()),
            Entry(6f, july.toFloat()),
            Entry(7f, aug.toFloat()),
            Entry(8f, sept.toFloat()),
            Entry(9f, oct.toFloat()),
            Entry(10f, nov.toFloat()),
            Entry(11f, dec.toFloat()),
        )

        setUpChart(entries)
    }
}