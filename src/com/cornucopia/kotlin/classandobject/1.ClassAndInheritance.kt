package com.cornucopia.kotlin.classandobject

/**
 * kotlin-类与对象-类与继承.
 * @author cornucopia
 * @version 1.0
 * @since 2020/1/1
 */
fun main() {
    testDerived()
}

//1.构造函数
//1.1 主构造函数

//1.1.1 主构造函数
class Person private constructor(name: String, age: Int) {

}

//1.1.2 如果主构造函数没有任何注解或者可见性修饰符，可以省略这个`constructor`关键字。
class Person1(name: String, age: Int) {

}

//1.1.3 主构造函数中不能包含任何的代码。初始化的代码可以放到init关键字为前缀的`初始化块`中。
class Person2(name: String) {
    var firstProperty = "First property:$name".also { println(it) }

    init {
        println("First initializer block that prints $name")
    }

    var secondProperty = "Second property:${name.length}".also {
        println(it)
    }

    init {
        println("Second initializer block that prints $name")
    }

}

//1.2 次构造函数

//1.2.1 次构造函数demo
class Person3 {
    var children: MutableList<Person3> = mutableListOf<Person3>()

    constructor(parent: Person3) {
        parent.children.add(this)
    }
}
//1.2.2 如果类有主构造函数，每个次构造函数需要委托给主构造函数，可以直接委托或者通过别的次构造函数间接委托。
//      被委托的主构造函数，可以使用`this`代替。

//1.2.3 次构造函数的执行位于所有初始化块的代码之后。
class Person4(val name: String) {
    var children: MutableList<Person4> = mutableListOf()

    constructor(name: String, parent: Person4) : this(name) {
        parent.children.add(this)
    }
}

//1.2.4 一个非抽象类，默认有一个不带参数的可见性为public的主构造函数。如果需要带有非默认可见性的主构造函数。
class Person5 private constructor() {

}

//1.2.5 在JVM上，如果主构造函数的所有参数都有默认值，编译器回生成一个额外的无参构造函数，它将使用默认值。
//    var person6=Person6()
//    person6.run {
//        println(this.name) -> a
//    }
class Person6(val name: String = "a")


//1.3 创建类的实例
class Invoice {

}

fun testCreator() {
    val invoice = Invoice()
}

//1.4 继承
//1.4.1 kotlin中所有类有一个共同超类Any,对于没有超类型声明的类是默认超类。
//1.4.2 Any有三个方法equals(),hashCode(),toString()。
//1.4.3 class关键字默认声明的是final类，open关键字可以使其可以被继承。
//1.4.4 派生类有一个主构造函数，基类可以用派生类主构造函数的参数就地初始化。
open class Base(p: Int)

class Derived(p: Int) : Base(p)
//1.4.5 派生类没有主构造函数，那么每个次构造函数必须使用super关键字初始化其基类型,或委托给另外一个构造函数做到这一点
open class View(name: String, age: Int)

class MyView : View {
    constructor(name: String, int: Int) : super(name, int)
    constructor(name: String) : this(name, 10)
}

//1.5 覆盖
//1.5.1 覆盖方法
//1.5.1.1 可覆盖的成员需要显式的修饰。
//1.5.1.2 open修饰到final类的成员不起作用
//1.5.1.3 override本身是开放的，也就是说，它可以在子类中覆盖。如果你想禁止再次覆盖，使用final关键字。
open class Shape {
    open fun draw() {

    }

    fun fill() {

    }
}

open class Circle() : Shape() {
    override fun draw() {
        super.draw()
    }
}

class CircleChild() : Circle() {

    override fun draw() {
        super.draw()
    }
}

fun testCover() {
}

//1.5.2 覆盖属性
//1.5.2.1 每个声明的属性可以由具有初始化器的属性或者具有get方法的属性覆盖。
//1.5.2.2 一个var属性覆盖一个val属性，但是反之则不行。
open class Shape1 {
    open val v = 0
}

class Rectangle : Shape1() {
    override val v: Int = 4
}

//1.5.3 派生类初始化顺序
open class Shape2(name: String) {
    init {
        println("init base") //1
    }

    open val size: Int = name.length.also { println("init size in Base:$it") } //2
}

class Rectangle1(name: String, val lastname: String) : Shape2(name) {
    init {
        println("init Rectangle1") //3
    }

    override val size: Int = (super.size + lastname.length).also { println("init size in Rectangle1:$it") } //4
}

fun testDerived() {
    var rectangle = Rectangle1("lw", "lj")

}

//1.5.4 调用超类实现
open class Rectangle2 {
    open fun draw() {
        println("Drawing a rectangle")
    }

    val borderColor: String get() = "black"
}

//1.5.4.1 一个内部类中访问外部类的超类，可以通过外部类的名限定的super关键字来实现。
class FilledRectangle : Rectangle2() {

    override fun draw() {
        super.draw()
        println("filling the rectangle")
    }

    val fillColor: String get() = super.borderColor

    inner class Filler {

        fun drawAndFill() {
            super@FilledRectangle.draw() //调用Rectangle的draw()实现

        }
    }
}

//1.5.5 覆盖规则
//同时继承和实现，需要覆盖方法,调用父类
open class Rectangle3 {
    open fun draw() {
    }
}

interface Polygon {
    fun draw() {

    }
}

class Square() : Rectangle3(), Polygon {
    override fun draw() {
        super<Polygon>.draw()
        super<Rectangle3>.draw()
    }
}

//1.5.6 抽象类
//1.5.6.1 可以用一个抽象成员覆盖一个非抽象的开放成员
open class Polygon3 {
    open fun draw() {}
}

abstract class Rectangle4 : Polygon3() {
    abstract override fun draw()
}
