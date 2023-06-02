package com.example.listedpoc.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.io.Serializable

data class DashboardModel(
    @Expose
    @SerializedName("status")
    var status: Boolean,
    @Expose
    @SerializedName("statusCode")
    var statusCode: Long,
    @Expose
    @SerializedName("message")
    var message: String?,
    @Expose
    @SerializedName("support_whatsapp_number")
    var supportWhatsappNumber: String?,
    @Expose
    @SerializedName("extra_income")
    var extraIncome: Double,
    @Expose
    @SerializedName("total_links")
    var totalLinks: Long,
    @Expose
    @SerializedName("total_clicks")
    var totalClicks: Long,
    @Expose
    @SerializedName("today_clicks")
    var todayClicks: Long,
    @Expose
    @SerializedName("top_source")
    var topSource: String?,
    @Expose@SerializedName("top_location")
    var topLocation: String?,
    var startTime: String?,
    @Expose
    @SerializedName("links_created_today")
    var linksCreatedToday: Long,
    @Expose
    @SerializedName("applied_campaign")
    var appliedCampaign: Long,
    var data: LinkData?,
):Serializable

data class LinkData(
    @Expose
    @SerializedName("recent_links")
    var recentLinks: MutableList<RecentLink>?,
    @Expose
    @SerializedName("top_links")
    var topLinks: MutableList<TopLink>?,
    @Expose
    @SerializedName("overall_url_chart")
    var overallUrlChart: Any?,
):Serializable

data class RecentLink(
    @Expose
    @SerializedName("url_id")
    var urlId: Long,
    @Expose
    @SerializedName("web_link")
    var webLink: String?,
    @Expose
    @SerializedName("smart_link")
    var smartLink: String?,
    var title: String?,
    @Expose
    @SerializedName("total_clicks")
    var totalClicks: Long,
    @Expose
    @SerializedName("original_image")
    var originalImage: String?,
    @Expose
    @SerializedName("thumbnail")
    var thumbnail: String?,
    @Expose
    @SerializedName("times_ago")
    var timesAgo: String?,
    @Expose
    @SerializedName("created_at")
    var createdAt: String?,
    @Expose
    @SerializedName("domain_id")
    var domainId: String?,
    @Expose
    @SerializedName("url_prefix")
    var urlPrefix: String?,
    @Expose
    @SerializedName("url_suffix")
    var urlSuffix: String?,
    var app: String?,
):Serializable

data class TopLink(
    @Expose
    @SerializedName("url_id")
    var urlId: Long,
    @Expose
    @SerializedName("web_link")
    var webLink: String?,
    @Expose
    @SerializedName("smart_link")
    var smartLink: String?,
    var title: String?,
    @Expose
    @SerializedName("total_clicks")
    var totalClicks: Long,
    @Expose
    @SerializedName("original_image")
    var originalImage: String?,
    @Expose
    @SerializedName("thumbnail")
    var thumbnail: String?,
    @Expose
    @SerializedName("times_ago")
    var timesAgo: String?,
    @Expose
    @SerializedName("created_at")
    var createdAt: String?,
    @Expose
    @SerializedName("domain_id")
    var domainId: String?,
    @Expose
    @SerializedName("url_prefix")
    var urlPrefix: String?,
    @Expose
    @SerializedName("url_suffix")
    var urlSuffix: String?,
    var app: String?,
):Serializable

