package com.onekitwx.UIKit

open class UILayoutPriority(rowValue: Float) {
    val required = UILayoutPriority(1000f);
    val defaultHigh = UILayoutPriority(750f);
    val dragThatCanResizeScene = UILayoutPriority(-1f)
    val sceneSizeStayPut = UILayoutPriority(-2f)
    val dragThatCannotResizeScene = UILayoutPriority(-3f)
    val defaultLow = UILayoutPriority(250f)
    var fittingSizeLevel = UILayoutPriority(50f)

    private var _rowValue: Float = 0f
    open val rowValue: Float
        get() = _rowValue
}