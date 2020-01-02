package com.cornucopia.kotlin.classandobject

/**
 * 接口
 * @author cornucopia
 * @version 1.0
 * @since 2020/1/2
 */
fun main() {
    testInterfaceProperty()
}

//1.kotlin接口既可以包含抽象方法的声明也可以包含实现。
interface MyInterface {
    fun bar()
    fun foo() {
        //可选的方法体
    }
}

//2.实现接口
//2.1 一个类或者对象可以实现一个或者多个接口
interface MyInterface1 {
    fun foo1()
}

interface Child : MyInterface, MyInterface1 {

}

//3.接口中的属性
//3.1 接口中定义的属性必须是抽象的或者提供访问器的。
//3.2 在接口中声明的属性不能有幕后字段(backing field)，因此接口中声明的访问器不能引用它们。
interface MyInterface2 {
    val prop: Int

    val propertyWithImplemntation: String
        get() = "foo"

    fun foo() {
        print(prop)
    }
}

class Child1 : MyInterface2 {
    override val prop: Int = 29
    override val propertyWithImplemntation: String
        get() = "zz"

    override fun toString(): String {
        return "$prop $propertyWithImplemntation"
    }

    override fun foo() {
        println("foo child1")
    }
}

fun testInterfaceProperty() {
    var child1 = Child1()
    child1?.run {
        println(this)
        this.foo()
    }
}

//4.接口的继承
interface Named {
    val name: String
}

interface NamedChild : Named {
    val firstName: String
    val lastName: String

    override val name: String
        get() = "$firstName $lastName"
}
data class Employee(override val firstName: String,
                    override val lastName: String):NamedChild

//5.解决覆盖冲突
interface A{
    fun foo(){
        println("A")
    }
    fun bar()
}
interface B{
    fun foo(){print("B")}
    fun bar(){print("bar")}
}
class C:A{
    override fun bar() {
        print("bar")
    }
}
class D:A,B{
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar()
    }
}

