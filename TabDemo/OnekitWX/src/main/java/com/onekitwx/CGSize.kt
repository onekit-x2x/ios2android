package com.onekitwx
open class CGSize {
    open var width: Float = 0f
    open var height: Float = 0f

    companion object {
        val zero = CGSize()
    }

    constructor(width: Float, height: Float) {
        this.width = width
        this.height = height
    }

    constructor() {
        this.width = 0f
        this.height = 0f
    }
}