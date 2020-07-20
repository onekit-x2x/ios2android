package com.onekitwx.UIKit

open class UILabel() : UIView() {
    private var _text: String? = null
    open var text: String?
        get() = _text
        set(text) {
            _text = text
        }


}