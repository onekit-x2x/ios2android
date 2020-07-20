package demo.tabdemo

import com.onekitwx.UIKit.*

class FirstViewController : UIViewController() {
    override fun onekit() {
        view = UIView()
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
/*
      //  this.view.autoresizesSubviews = false

        this.view.backgroundColor = UIColor.red
      //  this.view.translatesAutoresizingMaskIntoConstraints = false
        val view1  = UIView()
       // view1.translatesAutoresizingMaskIntoConstraints = false
        view1.frame = CGRect(100f,100f,50f,50f)
        var arm1 = UIView.AutoresizingMask()

        arm1 = arm1.union(UIView.AutoresizingMask.flexibleWidth)
         arm1 = arm1.union(UIView.AutoresizingMask.flexibleHeight)
         arm1 = arm1.union(UIView.AutoresizingMask.flexibleBottomMargin)
         arm1 = arm1.union(UIView.AutoresizingMask.flexibleTopMargin)

        view1.backgroundColor = UIColor.blue
        view1.autoresizingMask = arm1
        this.view.addSubview(view1)

 */

        val _redview = UIView();
        _redview.backgroundColor = UIColor.red;
        _redview.translatesAutoresizingMaskIntoConstraints = false;
        val _blueview = UIView()
        _blueview.backgroundColor = UIColor.blue;
        _blueview.translatesAutoresizingMaskIntoConstraints = false;

        this.view.addSubview(_redview);
        this.view.addSubview(_blueview);
        //redview在垂直方向上距离边界为50
        val constraint1 = NSLayoutConstraint(_redview,NSLayoutConstraint.Attribute.top,NSLayoutConstraint.Relation.equal,this.view,NSLayoutConstraint.Attribute.top,1f,50f);
        //设置redview的宽
        val constraint2 = NSLayoutConstraint(_redview,NSLayoutConstraint.Attribute.width,NSLayoutConstraint.Relation.equal,null,NSLayoutConstraint.Attribute.width,1f,200f);
        //设置reaview的高
        val constraint3 = NSLayoutConstraint(_redview,NSLayoutConstraint.Attribute.height,NSLayoutConstraint.Relation.equal,null,NSLayoutConstraint.Attribute.width,1f,50f);
        //设置readview居中
        val constraint4 = NSLayoutConstraint(_blueview,NSLayoutConstraint.Attribute.centerX,NSLayoutConstraint.Relation.equal,this.view,NSLayoutConstraint.Attribute.centerX,1f,0f);
        //设置blueview宽度是redview的0.5倍
        val constraint5 = NSLayoutConstraint(_blueview,NSLayoutConstraint.Attribute.width,NSLayoutConstraint.Relation.equal,_redview,NSLayoutConstraint.Attribute.width,0.5f,50f);
        //设置blueview和redview等高
        val constraint6 = NSLayoutConstraint(_blueview,NSLayoutConstraint.Attribute.height,NSLayoutConstraint.Relation.equal,_redview,NSLayoutConstraint.Attribute.height,1f,0f);
        //redview和blueview的右边界对齐
        val constraint7 = NSLayoutConstraint(_redview,NSLayoutConstraint.Attribute.trailing,NSLayoutConstraint.Relation.equal,_blueview,NSLayoutConstraint.Attribute.trailing,1f,0f);
        //blueview顶部距离redview为50
        val constraint8 = NSLayoutConstraint(_blueview,NSLayoutConstraint.Attribute.top,NSLayoutConstraint.Relation.equal,_redview,NSLayoutConstraint.Attribute.bottom,1f,50f);

        this.view.addConstraint(constraint1);
        this.view.addConstraint(constraint2);
        this.view.addConstraint(constraint3);
        this.view.addConstraint(constraint4);
        this.view.addConstraint(constraint5);
        this.view.addConstraint(constraint6);
        this.view.addConstraint(constraint7);
        this.view.addConstraint(constraint8);

    }
}
