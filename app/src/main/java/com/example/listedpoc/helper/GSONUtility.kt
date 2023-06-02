package com.example.listedpoc.helper

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object GSONUtility {

    fun getJSON(model :Any):String{
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create().toJson(model).toString()
    }

    fun<T> jsonToModel(json :String,classType:Class<T>):T{
        return Gson().fromJson(json,classType)
    }

    fun<T> jsonToModel(json :String,type: Type):ArrayList<T>{
        return Gson().fromJson(json,type)
    }

    fun <T> typeOfList(): Type {
        return object : TypeToken<ArrayList<T>>() {}.type
    }

}