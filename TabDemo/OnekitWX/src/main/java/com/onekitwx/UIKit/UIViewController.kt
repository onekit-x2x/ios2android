package com.onekitwx.UIKit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

 abstract class UIViewController : AppCompatActivity() {
     companion object {
         var context: UIViewController? = null
     }
     private var _view:UIView? = null
     open var view: UIView
         get() = this._view!!
         set(value) {
             _view = value
         }

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         context = this
         onekit()
         setContentView(_view)
         viewDidLoad()
     }

     open fun viewDidLoad() {

     }

     abstract fun onekit()
 }