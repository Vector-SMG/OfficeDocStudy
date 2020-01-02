package com.cornucopia.kotlin.classandobject

/**
 * 扩展
 * @author cornucopia
 * @version 1.0
 * @since 2020/1/2
 */
fun main() {
    testShape5()
}

//kotlin能够扩展一个类的新功能而无需继承该类或者使用装饰者这样的设计模式。可以为一个你不能修改的，来自第三方库
//的类编写一个新的扩展函数。

//1.扩展函数。
//1.1 声明一个扩展函数，我们需要用一个接收类型作为前缀。
//1.2 this表示接收对象。比如:MutableList
fun MutableList<Int>.swap(index1:Int,index2:Int){
    val tmp=this[index1]
    this[index1]=this[index2]
    this[index2]=tmp
}
fun testExtend(){
    val list= mutableListOf(1,2,3)
    list.swap(0,2)
    println(list)
}
//2.扩展是静态解析的。
//2.1 扩展函数是由函数调用的表达式的类型决定的，不是由表达式运行时求值决定的。
open class Shape4
class Rectangle5:Shape4()
fun Shape4.getName()="Shape"
fun Rectangle5.getName()="Rectangle"
fun printClassName(s:Shape4){
    println(s.getName()) //shape
}

//2.2 如果两个函数由相同的接收类型，相同的名字，一般是成员函数优先级比较高。
class Shape5{
    fun printFunctionType(){
        println("class method")
    }
}
fun Shape5.printFunctionType(){
    println("Extension function")
}

fun testShape5(){
    Shape5().printFunctionType()
}