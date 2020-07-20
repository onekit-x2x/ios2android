package com.onekitwx.UIKit.core

import android.view.View
import com.onekitwx.UIKit.NSLayoutConstraint
import com.onekitwx.UIKit.UIView

class UIAutoLayout(view: UINode) {
    class Node {
        val constraints: MutableList<NSLayoutConstraint> = ArrayList()
    }
    private val lines: HashMap<Int, Boolean?> = HashMap()
    private val allNode: HashMap<Int, Node> = HashMap()
    private val root: UIView = view as UIView
    private val initConstraints: MutableList<NSLayoutConstraint> = ArrayList()

    init {
        getConstraint(root)
    }

    fun run() {

    }

    private fun getConstraint(view: UIView) {
        for (constraint in view.constraints) {
            lines[constraint.hashCode()] = null
            //
            val view1 = constraint.view1 as View
            val id1 = view1.hashCode()
            val node1: Node
            node1 = if (allNode.containsKey(id1)) {
                allNode[id1]!!
            } else {
                Node()
            }
            node1.constraints.add(constraint)
            //
            if (constraint.view2 != null) {
                val view2 = constraint.view2 as View
                val id2 = view2.hashCode()
                val node2: Node
                node2 = if (allNode.containsKey(id2)) {
                    allNode[id2]!!
                } else {
                    Node()
                }
                node2.constraints.add(constraint)
                allNode[id2] = node2
            }
            if(constraint.view2==null || constraint.view2.hashCode()==root.hashCode()){
                initConstraints.add(constraint)
            }
            //
            allNode[id1] = node1
        }
        for (i in 0 until view.childCount) {
            val v = view.getChildAt(i)
            if (v is UIView) {
                getConstraint(v)
            }
        }
    }
}