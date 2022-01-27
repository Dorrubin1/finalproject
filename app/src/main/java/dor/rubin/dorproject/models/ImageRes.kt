package dor.rubin.dorproject.models

import android.content.Context

data class ImageRes(val name:String="",val type:String="",val packageId:String="") {
    fun getResourceId(context: Context): Int {
       return context.resources.getIdentifier(name,type,packageId)
    }
}