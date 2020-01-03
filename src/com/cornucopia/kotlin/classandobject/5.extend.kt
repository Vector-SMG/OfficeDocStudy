package com.cornucopia.kotlin.classandobject

/**
 * 扩展
 * @author cornucopia
 * @version 1.0
 * @since 2020/1/2
 */
fun main() {
    MyClass.printCompanion()
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
//3. 可空接收者
//3.1 注意可以为可空的接收者类型定义扩展。这样的扩展可以在对象变量上调用，即使其值为null，并且可以在函数体内检测
fun Any?.toString():String{
    if(this==null){
        return "null"
    }
    return toString()
}

//4. 扩展属性
//4.1 扩展没有实际的将成员插入类中，因此对扩展属性来说幕后字段是无效的。
//4.2 因为扩展没有实际的将成员插入类中，不能有初始化器。
val <T> List<T>.lastIndex:Int
    get() = size-1

//5. 伴生对象的扩展
//5.1 一个类定义了伴生对象，你也可以为伴生对象定义扩展或者属性。就像伴生对象的常规成员一样,使用类名作为限定符
//调用伴生对象的扩展成员。
class MyClass{
    companion object{
    }
}
fun MyClass.Companion.printCompanion(){
    println("companion")
}

//6. 扩展的作用域
//6.1 包，在包里顶层定义扩展,包内全部可以使用;在包外，需要在调用方导入它。

//7. 扩展声明为成员


