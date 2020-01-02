package com.cornucopia.kotlin.classandobject

import java.lang.AssertionError

/**
 * kotlin-类与对象-属性与字段
 * @author cornucopia
 * @version 1.0
 * @since 2020/1/1
 */
fun main() {
    testBackingField()
}

//1.声明属性
//1.1 var声明可变，val声明只读的。
class Person7 {
    var name: String? = null
    var age: Int? = null
}

fun testProperty() {
    val person7 = Person7()
    println(person7.name)
}

//2.Getters与Setters
//2.1 var 有get，set方法;val只有get方法
//2.2 set方法可以定义访问器，不需要定义其实现
class Person8() {
    var name: String?="0"
        private set

    val age:Int?
        get() = 0
}

//3.幕后字段
//3.1 如果在set方法中，使用this.name=value，会造成set方法中调用自身，导致死循环。如何解决？才有幕后字段
//3.2 kotlin，如果属性至少一个访问器使用默认实现，那么kotlin会自动提供幕后字段，使用关键字`field`.幕后字段
//主要用于自定义getter和setter中，并且只能在getter和setter中访问。

//参考:https://www.jianshu.com/p/c1a4c04eb33c
class Person9(){
    var name:String="lw"
        set(value) {
            field=value
        }
    var age:Int=0
        set(value){
            name="lw2"
            field=12
        }
        get() = name.length
}
fun testBackingField(){
    var person9=Person9()
    person9.name="lw1"
    person9.age=13
    println(person9.name)
    println(person9.age)
}

//幕后属性
//1.1 有时候我们需要一个属性：对外表现为只读，对内表现为可读可写。我们将这个属性称之为幕后属性。
//1.2 _table即幕后属性。
class Person10{
    private var _table:Map<String,Int>?=null
    public val table:Map<String,Int>
        get() {
            if(_table==null){
                _table=HashMap()
            }
            return _table?:throw AssertionError("Set to null by another thread")
        }
}
fun testBackingProperty(){
    var person10=Person10()
}


