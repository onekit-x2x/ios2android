package com.onekitwx.UIKit.core

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import com.onekitwx.CGRect
import android.util.AttributeSet
import android.util.Size
import android.view.ViewGroup
import com.onekitwx.UIKit.NSLayoutConstraint
import com.onekitwx.UIKit.UIView
import com.onekitwx.UIKit.UIViewController

abstract class UINode : ViewGroup {
    protected var isAutolayout: Boolean = false

    open class LayoutParams : ViewGroup.LayoutParams {
        var originRect: Rect? = null
        var rect: Rect? = null
        var frame = CGRect.zero
        var autoresizingMask = UIView.AutoresizingMask()

        constructor(w: Int, h: Int) : super(w, h)
        constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

        ////////
        val constraints: MutableList<NSLayoutConstraint> = ArrayList()
        var l: Int? = null
        var t: Int? = null
        var w: Int? = null
        var h: Int? = null
    }

    //////////////////////////////////////////////////////////////////////////////
    companion object {
        fun dp2px(value: Float): Int {
            val v = UIViewController.context!!.resources.displayMetrics.density
            return (v * value + 0.5f).toInt()
        }

        fun px2dp(value: Int): Float {
            val v = UIViewController.context!!.resources.displayMetrics.density
            return value / v + 0.5f
        }
/*
        fun sp2px(value: Float): Int {
            val v = UIViewController.context!!.resources.displayMetrics.scaledDensity
            return (v * value + 0.5f).toInt()
        }
        fun px2sp(value: Int): Float {
            val v = UIViewController.context!!.resources.displayMetrics.scaledDensity
            return value / v + 0.5f
        }*/
    }

    //////////////////////////////////////////
    constructor() : super(UIViewController.context!!) {

        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {

    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return LayoutParams(0, 0)
    }

    override fun generateLayoutParams(p: ViewGroup.LayoutParams?): ViewGroup.LayoutParams {
        return if (p != null) {
            LayoutParams(p.width, p.height)
        } else {
            generateDefaultLayoutParams()
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return LayoutParams(context, attrs)
    }

    protected var oldSize: Size? = null

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (parent !is UINode) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            if (isAutolayout) {
                UIAutoLayout(this).run()
            }
            return
        }
        val parent = parent as UINode
        if (parent.isAutolayout) {
            return
        }
        val layoutParams = layoutParams as LayoutParams
        this.setMeasuredDimension(layoutParams.rect!!.width(), layoutParams.rect!!.height())
    }

    protected fun changeChild(OW: Int, OH: Int, NW: Int, NH: Int, lp: LayoutParams): Rect {
        val autoresizingMask = lp.autoresizingMask
        if (autoresizingMask.value == 0) {
            return lp.rect!!
        }
        val rect = lp.originRect!!
        val ol = rect.left
        val ow = rect.width()
        val or = OW - ol - ow
        val nl: Int
        val nw: Int
        if (autoresizingMask.contains(UIView.AutoresizingMask.flexibleLeftMargin)) {
            if (autoresizingMask.contains(UIView.AutoresizingMask.flexibleWidth)) {
                if (autoresizingMask.contains(UIView.AutoresizingMask.flexibleRightMargin)) {
                    nl = ol * NW / OW
                    nw = ow * NW / OW
                } else {
                    nl = ((NW - OW) + (ol + ow)) / (1 + ow / ol)
                    nw = ((NW - OW) + (ol + ow)) / (1 + ol / ow)
                }
            } else {
                nw = ow
                nl = if (autoresizingMask.contains(UIView.AutoresizingMask.flexibleRightMargin)) {
                    ((NW - OW) + (ol + or)) / (1 + or / ol)
                } else {
                    (NW - OW) + ol
                }
            }
        } else {
            nl = ol
            nw = if (autoresizingMask.contains(UIView.AutoresizingMask.flexibleWidth)) {
                if (autoresizingMask.contains(UIView.AutoresizingMask.flexibleRightMargin)) {
                    ((NW - OW) + (ow + or)) / (1 + ol / or)
                } else {
                    (NW - OW) + ow
                }
            } else {
                ow
            }
        }
        val ot = rect.top
        val oh = rect.height()
        val ob = OH - ot - oh
        val nt: Int
        val nh: Int
        if (autoresizingMask.contains(UIView.AutoresizingMask.flexibleTopMargin)) {
            if (autoresizingMask.contains(UIView.AutoresizingMask.flexibleHeight)) {
                if (autoresizingMask.contains(UIView.AutoresizingMask.flexibleBottomMargin)) {
                    nt = ot * NH / OH
                    nh = oh * NH / OH
                } else {
                    nt = ((NH - OH) + (ot + oh)) / (1 + oh / ot)
                    nh = ((NH - OH) + (ot + oh)) / (1 + oh / ot)
                }
            } else {
                nh = oh
                nt = if (autoresizingMask.contains(UIView.AutoresizingMask.flexibleBottomMargin)) {
                    ((NH - OH) + (ot + ob)) / (1 + ob / ot)
                } else {
                    (NH - OH) + ot
                }
            }
        } else {
            nt = ot
            nh = if (autoresizingMask.contains(UIView.AutoresizingMask.flexibleHeight)) {
                if (autoresizingMask.contains(UIView.AutoresizingMask.flexibleBottomMargin)) {
                    ((NH - OH) + (oh + ob)) / (1 + ot / ob)
                } else {
                    (NH - OH) + oh
                }
            } else {
                oh
            }
        }
        lp.frame = CGRect(
            px2dp(nl),
            px2dp(nt),
            px2dp(nw),
            px2dp(nh)
        )
        return Rect(nl, nt, nl + nw, nt + nh)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        if (!changed) {
            return
        }
        if (isAutolayout) {
            for (i in 0 until childCount) {
               // val child = getChildAt(i)
               // val lp = child.layoutParams as LayoutParams
               // child.layout(lp.l!!, lp.t!!, lp.w!!, lp.h!!)
            }
        } else {
            for (i in 0 until childCount) {
                val child = getChildAt(i)
                val r = (child.layoutParams as LayoutParams).rect!!
                child.layout(r.left, r.top, r.right, r.bottom)
            }
        }
    }
}