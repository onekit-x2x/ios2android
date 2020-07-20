package com.onekitwx.UIKit

import android.content.Context
import android.graphics.Rect
import com.onekitwx.CGRect
import android.util.AttributeSet
import android.util.Size
import android.view.View
import android.view.ViewGroup
import com.onekitwx.OptionSet
import com.onekitwx.UIKit.core.UINode

open class UIView : UINode {
    open class AutoresizingMask : OptionSet {
        companion object {

            val flexibleLeftMargin = AutoresizingMask(1)
            val flexibleWidth = AutoresizingMask(2)
            val flexibleRightMargin = AutoresizingMask(4)
            val flexibleTopMargin = AutoresizingMask(8)
            val flexibleHeight = AutoresizingMask(16)
            val flexibleBottomMargin = AutoresizingMask(32)
        }
        constructor() : super(0)

        constructor(value: Int) : super(value)

        open fun union(autoresizingMask: AutoresizingMask): AutoresizingMask {
            return super.union(autoresizingMask)
        }

        open fun contains(autoresizingMask: AutoresizingMask): Boolean {
            return super.contains(autoresizingMask)
        }
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        /* if(translatesAutoresizingMaskIntoConstraints){
             return
         }*/
         if(!autoresizesSubviews){
             return
         }
        if (oldSize == null) {
            oldSize = Size(w, h)
            return
        }
        val childCount = childCount-1
        for (i in 0..childCount) {
            val child = getChildAt(i)
            val lp = child.layoutParams as LayoutParams
            lp.rect = changeChild(oldSize!!.width,oldSize!!.height,w,h,lp)
        }
        requestLayout()
    }
    //////////////////////////////////////////
    constructor() : super(){
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        init()
    }
    private fun init(){

    }

    private var _translatesAutoresizingMaskIntoConstraints: Boolean = true
    open var translatesAutoresizingMaskIntoConstraints: Boolean
        get() = _translatesAutoresizingMaskIntoConstraints
        set(translatesAutoresizingMaskIntoConstraints) {
            _translatesAutoresizingMaskIntoConstraints = translatesAutoresizingMaskIntoConstraints
        }

    private var _autoresizesSubviews: Boolean = true
    open var autoresizesSubviews: Boolean
        get() = _autoresizesSubviews
        set(autoresizesSubviews) {
            _autoresizesSubviews = autoresizesSubviews
            this.layoutParams = layoutParams
        }
    //
    open var autoresizingMask: AutoresizingMask
        get() = if (layoutParams != null) {
            (layoutParams as LayoutParams).autoresizingMask
        } else {
            AutoresizingMask()
        }
        set(autoresizingMask) {
            val layoutParams = this.layoutParams as LayoutParams
            layoutParams.autoresizingMask = autoresizingMask
            this.layoutParams = layoutParams
        }
    open var frame: CGRect
        get() = if (layoutParams != null) {
            (layoutParams as LayoutParams).frame
        } else {
            CGRect.zero
        }
        set(frame) {
            var layoutParams = this.layoutParams as? LayoutParams
            if(layoutParams==null){
                layoutParams = generateDefaultLayoutParams()
            }
            val left = dp2px(frame.origin.x)
            val top = dp2px(frame.origin.y)
            val width = dp2px(frame.size.width)
            val height = dp2px(frame.size.height)
            val rect = Rect(left, top, left + width, top + height)
            layoutParams.originRect = rect
            layoutParams.rect = rect
            //
            layoutParams.frame = frame
            this.layoutParams = layoutParams
        }

    override fun attachViewToParent(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.attachViewToParent(child, index, params)
        if(child is UIView){
            child.translatesAutoresizingMaskIntoConstraints = translatesAutoresizingMaskIntoConstraints
            child.isAutolayout = isAutolayout
        }
    }
    /////////////////////////////////////////////
    val _constraints : MutableList<NSLayoutConstraint> = ArrayList()
    open val constraints:List<NSLayoutConstraint>
            get() = _constraints.toList()
    open fun addConstraint(constraint: NSLayoutConstraint) {
        _constraints.add(constraint)
        isAutolayout = true
        //
        val view1 = constraint.view1 as View
        val layoutParams = view1.layoutParams as UINode.LayoutParams
        layoutParams.constraints.add(constraint)
        //
        view1.layoutParams = layoutParams
    }
    /////////////////////////////////////////////
    private var _backgroundColor: UIColor? = null
    open var backgroundColor: UIColor?
        get() = _backgroundColor
        set(backgroundColor) {
            _backgroundColor = backgroundColor
            setBackgroundColor(
                if (_backgroundColor != null) {
                    _backgroundColor!!.self!!.toArgb()
                } else {
                    0
                }
            )
        }

    fun addSubview(view: UIView) {
        addView(view)
    }

}