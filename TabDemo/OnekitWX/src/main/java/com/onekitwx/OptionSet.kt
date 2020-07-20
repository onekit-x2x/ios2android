package com.onekitwx

import kotlin.reflect.full.createInstance

abstract class OptionSet {
    var value:Int = 0
    public constructor(){

    }
    constructor(value: Int){
        this.value=value
    }
    fun <T:OptionSet> union(obj:T):T{
         val clazz = this::class
        val result = clazz.createInstance() as T
        result.value = this.value+obj.value
        return result
    }

    fun  <T:OptionSet>  contains(obj: T): Boolean {
        return (this.value and obj.value)>0
    }
}