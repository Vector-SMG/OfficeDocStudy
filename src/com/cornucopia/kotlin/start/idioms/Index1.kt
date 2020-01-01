package com.cornucopia.kotlin.start.idioms


/**
 * kotlin-习惯用法(二)-let,with,run,apply,also函数的使用
 * kotlin中的源码标准库(Standard.kt)中提供了一些kotlin扩展的内置函数可以优化kotlin的编码。
 * Standard.kt是kotlin库的一部分，它定义了一些基本函数。这个源文件虽然不到50行代码，但是函数功能都非常强大。
 *
 * @author cornucopia
 * @version 1.0
 * @since 2019/12/29
 */
fun main() {
    testAlso()
}

//--------------------------------------lambda表达式demo相关类------------------------------//
interface OnClickListener {
    fun onClick(str: String, int: Int)
    fun onSingleTap()
}

open class MyOnClickListener : OnClickListener {
    override fun onClick(str: String, int: Int) {

    }


    override fun onSingleTap() {
    }

}

class View {
    private lateinit var listener_normal: OnClickListener
    private lateinit var listener_lambda: (str: String, int: Int) -> Unit


    fun setOnClickListener(listener: (str: String, int: Int) -> Unit) {
        this.listener_lambda = listener
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener_normal = listener
    }


    fun onClick(str: String, int: Int) {
        this.listener_lambda?.let {
            it.invoke(str, int)
        }

        this.listener_normal?.let {
            it.onClick(str, int)
        }

    }

}
//--------------------------------------lambda表达式demo相关类------------------------------//

//1.kotlin的lambda的简化
//1.1 lambda匿名函数，lambda表达式，只支持抽象方法模型，也就是说设计的接口只有一个抽象方法，才符合lambda表达式的规则
//，多个回调方法不支持
fun testLambda() {
//    var viewJava=ViewJava()
    //1.2 kotlin调用java代码回调(一),普通方式
//     viewJava.setOnClickListener(object:OnClickListenerForJava{
//         override fun onClick() {
//              println("kotlin调用java回调 普通方式")
//         }
//     })

    //1.3 kotlin调用java回调(二),lambda方式
//    viewJava.setOnClickListener {
//        println("kotlin调用java回调 lambda方式")
//    }

    var view = View()
    //1.4 kotlin中实现一个接口的回调,普通方式
    view.setOnClickListener(object : OnClickListener {

        override fun onClick(str: String, int: Int) {
        }

        override fun onSingleTap() {
        }
    })
    //1.5 kotlin中实现只有一个方法的接口回调,lambda方式
    //1.5.1 如果没有参数，可以直接{}
    //1.5.2 如果带类型，str:String,int:Int-> 执行代码
    //1.5.3 如果不带类型,str,int-> 执行代码
    view.setOnClickListener {
        //                   1.4.0
        //str:String,int:Int 1.4.1 带类型
            str, int ->
        //        1.4.2 不带类型，自动类型推断
        println("str: $str int:$int")
    }
    view.onClick("lw", 12)
}

//2.let,内联扩展函数，实际上是一个作用域函数，让一个变量在一个特定的作用域范围内。
//2.1 在函数体内使用it替代object对象去访问共有的属性和方法
//2.2 判断object是否为null，去执行不为null的代码
fun testLet() {
    val a = 1
    a.let {
        println(it)
    }

    val b = "你好"
    b?.let {
        println("b不为空!!!")
    }
}

//3.with,调用一个类的多个方法时，可以省去类名重复，直接调用类的方法即可。
data class User(val name: String, val age: Int)

fun testWith() {
    val user = User("lw", 29)
    //3.1 原始写法
    val result = with(user, {
        println("my name is $name,I am $age years old")
        1000
    })
    //3.2 根据最后一个函数作为参数的时候可以写在圆括号外面的原理
//    val result1=with(user){
//        println("my name is $name,I am $age years old")
//        1000
//    }
    println(result)
}

//4.内联扩展函数run
//4.1 run函数实际上可以说是let和with的结合体,run函数只接收一个lambda函数作为参数，以闭包形式返回。
//4.2 弥补了let函数体内必须使用it参数替代对象，弥补了with函数传入对象判空问题。
fun testRun() {
    val user = User("lw", 111)
    val result = user.run {
        println("my name is $name,I am $age years old")
        1000
    }
    println("result:$result")
}

//5.内联扩展函数之apply
//5.1 apply和run的区别在于:apply的返回值是返回传入对象本省;run的返回值在于最后一行。
//5.2 一般用于对一个对象属性进行赋值。

class Person(var name: String){

    override fun toString(): String {
        return "name:$name"
    }
}

class UserCopy(var name:String,var age:Int,var person: Person) {

    var city="东莞"

    override fun toString(): String {
        return "name:$name age:$age city:$city person:$person"
    }
}
fun testApply() {
    var person=Person("lj")
    val user = UserCopy("lw", 111,person)
//    val result = user.apply {
//        this.name="lw1"
//        this.age=11
//        this.city="深圳"
//    }
//    println("result:$result")

    //apply多嵌套级别判空
    var result1= user?.apply {
        this.name="lzf"
    }?.person.apply {
        this.name="lj1"
    }
    println("user:$user")
}

//6.内联扩展函数之also
//6.1 also函数和let很像,let返回函数体内最后一行;also返回传入的对象。
fun testAlso(){
    val result="test".also {
        println(it.length)
        1000
    }
    println(result)
}







