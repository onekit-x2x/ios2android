package com.onekitwx.UIKit
import com.onekitwx.OptionSet

open class NSLayoutConstraint {

    enum class Relation {

        lessThanOrEqual,

        equal,

        greaterThanOrEqual
    }

    enum class Attribute {
        left,

        right,

        top,

        bottom,

        leading,

        trailing,

        width,

        height,

        centerX,

        centerY,

        lastBaseline,


        firstBaseline,


        leftMargin,

        rightMargin,

        topMargin,

        bottomMargin,

        leadingMargin,

        trailingMargin,

        centerXWithinMargins,

        centerYWithinMargins,


        notAnAttribute
    }

    open class FormatOptions : OptionSet {
        companion object {
            val alignAllLeftUIView = FormatOptions(1)

            val alignAllRightUIView = FormatOptions(2)

            val alignAllTopUIView = FormatOptions(4)

            val alignAllBottomUIView = FormatOptions(8)

            val alignAllLeadingUIView = FormatOptions(16)

            val alignAllTrailingUIView = FormatOptions(32)

            val alignAllCenterXUIView = FormatOptions(64)

            val alignAllCenterYUIView = FormatOptions(128)

            val alignAllLastBaselineUIView = FormatOptions(256)

            val alignAllFirstBaselineUIView = FormatOptions(512)

            val alignmentMaskUIView = FormatOptions(1024)

            val directionLeadingToTrailingUIView = FormatOptions(2048)

            val directionLeftToRightUIView = FormatOptions(4096)

            val directionRightToLeftUIView = FormatOptions(8192)


            val directionMaskUIView = FormatOptions(16384)


            val spacingBaselineToBaselineUIView = FormatOptions(32768)

            val spacingMaskUIView = FormatOptions(65536)
        }

        constructor() : super(0)

        constructor(value: Int) : super(value)

        fun union(formatOptions: FormatOptions): FormatOptions {
            return super.union(formatOptions)
        }

        fun contains(formatOptions: FormatOptions): Boolean {
            return super.contains(formatOptions)
        }
    }

    /////////////////////////////////////////
    companion object {
        fun constraints(
            format: String,
            opts: FormatOptions,
            metrics: Map<String, UIView>,
            views: Map<String, UIView>
        ): NSLayoutConstraint {
            return NSLayoutConstraint()
        }
    }

    var view1: Any? = null
    var attr1: Attribute? = null
    var relation: Relation? = null
    var view2: Any? = null
    var attr2: Attribute? = null
    var multiplier: Float = 0f
    var constant: Float = 0f

    constructor()
    constructor(
        view1: Any,
        attr1: Attribute,
        relation: Relation,
        view2: Any?,
        attr2: Attribute,
        multiplier: Float,
        constant: Float
    ) {
        this.view1 = view1
        this.attr1 = attr1
        this.relation = relation
        this.view2 = view2
        this.attr2 = attr2
        this.multiplier = multiplier
        this.constant = constant
    }
}
