package com.onekitwx.UIKit

import android.graphics.Color

class UIColor(red: Float, green: Float, blue: Float, alpha: Float) {
    var self:Color?=null;
    companion object {
         val white = UIColor(1f, 1f, 1f, 1f)
         val black = UIColor(0f, 0f, 0f, 1f)
         val red = UIColor(1f, 0f, 0f, 1f)
         val orange = UIColor(1f, .5f, 0f, 1f)
         val yellow = UIColor(0f, 1f, 1f, 1f)
         val green = UIColor(0f, 1f, 0f, 1f)
         val cyan = UIColor(1f, 1f, 0.5f, 1f)
         val blue = UIColor(0f, 0f, 1f, 1f)
         val magenta = UIColor(1f, 0f, 1f, 1f)
    }

    init {
        self= Color.valueOf(red,green,blue,alpha)
    }
}