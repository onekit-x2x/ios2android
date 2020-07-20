package com.onekitwx
open class CGPoint {
    open var x: Float = 0f
    open var y: Float = 0f

    companion object {
        val zero = CGPoint()
    }

    constructor(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    constructor() {
        this.x = 0f
        this.y = 0f
    }
}