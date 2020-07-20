package com.onekitwx;
open class CGRect {
    open var origin: CGPoint = CGPoint.zero
    open var size: CGSize = CGSize.zero

    companion object {
         val zero = CGRect(0f,0f,0f,0f)
    }
    constructor(x:Float,y:Float,width:Float,height:Float){
        origin =  CGPoint(x,y)
        size=CGSize(width,height)
    }
    constructor(){

    }
}