package com.example.listedpoc.helper

object PlatformUtils {

    fun parseErrorResponseFromJson(json: String?): String? {
        return try{
            GSONUtility.jsonToModel(json!!,String::class.java)
        }catch (e:Exception){
            null
        }
    }
}